package med.voll.api.model.consulta;

import med.voll.api.dto.CancelarConsultaDTO;
import med.voll.api.dto.DetalleConsultaDTO;
import med.voll.api.infra.ValidacionException;
import med.voll.api.model.medico.MedicoRepository;
import med.voll.api.model.paciente.PacienteRepository;
import med.voll.api.model.consulta.validacion.ValidarTodo;
import med.voll.api.model.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidarTodo> validador;


    public DetalleConsultaDTO reservar(DatosConsulta datos){

        if(!pacienteRepository.existsById(datos.idPaciente())){
            throw new ValidacionException("No existe un paciente con el id informado");
        }

        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionException("No existe un médico con el id informado");
        }

        //validaciones
        validador.forEach(v -> v.validar(datos));

        var medico = elegirMedico(datos);
        if(medico ==null){
            throw new ValidacionException("No existe un médico disponible");
        }
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
        return new DetalleConsultaDTO(consulta);
    }

    private Medico elegirMedico(DatosConsulta datos) {
        if(datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null){
            throw new ValidacionException("Es necesario elegir una especialidad cuando no se elige un médico");
        }

        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha());
    }


}
