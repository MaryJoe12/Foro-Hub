package med.voll.api.dto;

import med.voll.api.model.consulta.Consulta;

import java.time.LocalDateTime;

public record DetalleConsultaDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime fecha) {
    public DetalleConsultaDTO(Consulta consulta) {
        this(consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getPaciente().getId(),
                consulta.getFecha());
    }
}
