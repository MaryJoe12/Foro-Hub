package med.voll.api.model.consulta.validacion;

import med.voll.api.infra.ValidacionException;
import med.voll.api.model.consulta.DatosConsulta;
import med.voll.api.model.consulta.ReservaConsultas;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacionHorario implements ValidarTodo{
    public void validar(DatosConsulta datos){
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horario= fechaConsulta.getHour()<7;
        var horarioSalida= fechaConsulta.getHour()>18;
        if(domingo||horario||horarioSalida){
            throw new ValidacionException("Horario fuera de las horas de servicio");
        }
    }
}
