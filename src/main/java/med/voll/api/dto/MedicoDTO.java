package med.voll.api.dto;

import med.voll.api.model.medico.Especialidad;
import med.voll.api.model.medico.Medico;

public record MedicoDTO(
        Long id,
        String nombre,
        String email,
        String documento,
        Especialidad especialidad
) {
    public MedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad());
    }
}
