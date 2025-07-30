package med.voll.api.model.topico;

import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
        @NotNull Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso
) {
}
