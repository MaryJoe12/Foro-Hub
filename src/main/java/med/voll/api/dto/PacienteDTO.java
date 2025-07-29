package med.voll.api.dto;

import med.voll.api.model.paciente.Paciente;

public record PacienteDTO (Long id, String nombre, String email, String documentoIdentidad) {
    public PacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento_identidad());
    }
}
