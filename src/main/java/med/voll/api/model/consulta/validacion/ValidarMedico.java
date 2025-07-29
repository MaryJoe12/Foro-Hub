package med.voll.api.model.consulta.validacion;

import med.voll.api.infra.ValidacionException;
import med.voll.api.model.medico.MedicoRepository;
import med.voll.api.model.consulta.DatosConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedico implements ValidarTodo{

    @Autowired
    private MedicoRepository repository;

    public void validar(DatosConsulta datos){
        if(datos.idMedico()== null){
            return;
        }
        var medicoActivo = repository.findActivoById(datos.idMedico());
        if(!medicoActivo){
            throw new ValidacionException("El medico debe de estar activo");
        }
    }

}
