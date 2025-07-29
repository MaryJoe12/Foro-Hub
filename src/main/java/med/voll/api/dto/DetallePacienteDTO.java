package med.voll.api.dto;

import med.voll.api.model.paciente.Paciente;

public record DetallePacienteDTO(
        Long id,
        String nombre,
        String email,
        String documento_identidad,
        String telefono
) {
    public DetallePacienteDTO(Paciente paciente){
        this(paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDocumento_identidad(),
                paciente.getTelefono());
    }
}
