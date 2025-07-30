package med.voll.api.model.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity (name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean estado;
    private String titulo;
    private String mensaje;
    private String autor;
    private String curso;
    private LocalDateTime fechaCreacion;


    public Topico(DatosTopico datos) {
        this.id = null;
        this.estado = true;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = datos.autor();
        this.curso = datos.curso();
        this.fechaCreacion = LocalDateTime.now();
    }

    public void actualizar(@Valid ActualizarTopicoDTO datos) {
        if(datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if(datos.autor() != null) {
            this.autor = datos.autor();
        }
        if(datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if(datos.curso() != null) {
            this.curso = datos.curso();
        }
    }

}
