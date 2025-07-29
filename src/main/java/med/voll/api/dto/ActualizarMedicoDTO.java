package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.model.direccion.DatosDireccion;

public record ActualizarMedicoDTO(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {
}
