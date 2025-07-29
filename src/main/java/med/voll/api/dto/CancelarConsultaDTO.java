package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.consulta.MotivoCancelamiento;

public record CancelarConsultaDTO(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamiento motivo
) {
}
