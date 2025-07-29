package med.voll.api.model.consulta.validacion;

import med.voll.api.infra.ValidacionException;
import med.voll.api.model.paciente.PacienteRepository;
import med.voll.api.model.consulta.DatosConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPaciente implements ValidarTodo{

    @Autowired
    private PacienteRepository repository;

    public void validar(DatosConsulta datos){
        var pacienteActivo= repository.findActivoById(datos.idPaciente());
        if(!pacienteActivo){
            throw new ValidacionException("El paciente debe de estar activo");
        }
    }
}
