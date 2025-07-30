package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.model.topico.*;
import med.voll.api.model.topico.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
//prueba
@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Transactional  //modificar, agregar, cambiar algo en la base de datos
    @PostMapping
    public ResponseEntity <TopicoDTO> registrar(@RequestBody @Valid DatosTopico datos, UriComponentsBuilder uriCom){
        Topico topico= repository.save( new Topico(datos));
        TopicoDTO datosRespuesta = new TopicoDTO(topico);

        //201, nuevo registro en la base de datos
        URI uri= uriCom.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(datosRespuesta);
    }

    @GetMapping
    //cuantos datos por pagina el size
    public ResponseEntity<Page<TopicoDTO>> listar(@PageableDefault(size=10, sort= "titulo" ) Pageable paginas){

        return ResponseEntity.ok(repository.findByEstadoTrue(paginas)
                .map(TopicoDTO::new));
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid ActualizarTopicoDTO datos){
        Topico topico = repository.getReferenceById(datos.id());
        topico.actualizar(datos);
        return ResponseEntity.ok(new TopicoDTO(topico));

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        repository.deleteById(id);
        //devuelve 204
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> buscartopico (@PathVariable Long id){
        Topico topico = repository.getReferenceById(id);
        var datos= new TopicoDTO(topico);

        return ResponseEntity.ok(datos);
    }
}
