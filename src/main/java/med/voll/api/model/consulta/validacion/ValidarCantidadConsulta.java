package med.voll.api.model.consulta.validacion;

import med.voll.api.infra.ValidacionException;
import med.voll.api.model.consulta.ConsultaRepository;
import med.voll.api.model.consulta.DatosConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarCantidadConsulta implements ValidarTodo{

    @Autowired
    private ConsultaRepository repository;

    public void validar (DatosConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var paciente = repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if(paciente){
            throw new ValidacionException("Solo una consulta por dia por paciente");
        }
    }
}
