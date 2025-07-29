package med.voll.api.model.consulta.validacion;

import med.voll.api.infra.ValidacionException;
import med.voll.api.model.consulta.ConsultaRepository;
import med.voll.api.model.consulta.DatosConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoHorario implements ValidarTodo{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosConsulta datos){
        var medicoConsulta = repository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if (medicoConsulta){
            throw new ValidacionException("El medico ya tiene otra consulta en la fecha y hora");
        }
    }

}
