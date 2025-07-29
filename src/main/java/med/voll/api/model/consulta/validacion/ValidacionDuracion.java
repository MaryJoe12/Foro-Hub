package med.voll.api.model.consulta.validacion;

import med.voll.api.infra.ValidacionException;
import med.voll.api.model.consulta.DatosConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacionDuracion implements ValidarTodo{

    public void validar(DatosConsulta datos){
        var ahora= LocalDateTime.now();
        var fechConsulta= datos.fecha();
        var diferencia = Duration.between(ahora, fechConsulta).toMinutes();
        if(diferencia < 30){
            throw new ValidacionException("Las citas se tienen que realizar con 30 minutos de anticipacion");
        }
    }
}
