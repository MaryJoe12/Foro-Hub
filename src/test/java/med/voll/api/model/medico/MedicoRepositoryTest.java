package med.voll.api.model.medico;

import jakarta.persistence.EntityManager;
import med.voll.api.model.consulta.Consulta;
import med.voll.api.model.direccion.DatosDireccion;
import med.voll.api.model.paciente.DatosPaciente;
import med.voll.api.model.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//cuando necesitamos repository
@DataJpaTest
//usa la base de datos de sql real
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//usar la bd de test desde application-test.properties
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository repository;
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Devolver null, cuando medico existe pero esta ocupado en la fecha")
    void elegirMedicoAleatorioDisponibleEnLaFecha() {
        var lunesProximo10= LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var medico = registrarMedico("Medico 1", "medico@gmail.com", "123456", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Paciente 1", "paciente@gmail.com", "7890");
        registratconsulta(medico, paciente, lunesProximo10);

        var medicoLibre= repository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesProximo10 );

        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("Devolver medico, cuando medico existe en la fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario2() {
        var lunesProximo10= LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var medico = registrarMedico("Medico 1", "medico@gmail.com", "123456", Especialidad.CARDIOLOGIA);

        var medicoLibre= repository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesProximo10 );

        assertThat(medicoLibre).isEqualTo(medico);
    }

    private void registratconsulta(Medico medico, Paciente paciente, LocalDateTime fecha){
        em.persist(new Consulta(null, medico, paciente, fecha));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad){
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento){
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;

    }

    private DatosMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad){
        return new DatosMedico(
                nombre,email,"3385", documento, especialidad, datosDireccion()
        );
    }

    private DatosPaciente datosPaciente(String nombre, String email, String documento){
        return new DatosPaciente(
                nombre, email, "35960", documento, datosDireccion()
        );
    }

    private DatosDireccion datosDireccion(){
        return new DatosDireccion(
                "calle x", "25", "complemento y", "barrio", "ciudad z"
                , "46412", "estado"

        );
    }


}