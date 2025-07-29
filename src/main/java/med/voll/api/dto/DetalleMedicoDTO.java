package med.voll.api.dto;

import med.voll.api.model.direccion.Direccion;
import med.voll.api.model.medico.Especialidad;
import med.voll.api.model.medico.Medico;

public record DetalleMedicoDTO (
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        Especialidad especialidad,
        Direccion direccion
) {
    public DetalleMedicoDTO(Medico medico) {
        this(medico.getId(),
            medico.getNombre(),
            medico.getEmail(),
            medico.getDocumento(),
            medico.getTelefono(),
            medico.getEspecialidad(),
            medico.getDireccion()
        );
    }
}
