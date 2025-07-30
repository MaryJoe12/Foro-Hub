package med.voll.api.model.topico;

import java.time.LocalDateTime;

public record TopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        boolean estado,
        String autor,
        String curso
) {
    public TopicoDTO(Topico datos) {
        this(datos.getId(), datos.getTitulo(), datos.getMensaje(), datos.getFechaCreacion(), datos.getEstado(), datos.getAutor(), datos.getCurso());
    }
}
