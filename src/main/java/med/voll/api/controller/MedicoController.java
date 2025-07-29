package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.dto.ActualizarMedicoDTO;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.dto.RespuestaMedicoDTO;
import med.voll.api.model.direccion.DatosDireccion;
import med.voll.api.model.medico.DatosMedico;
import med.voll.api.model.medico.Medico;
import med.voll.api.model.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transactional  //modificar, agregar, cambiar algo en la base de datos
    @PostMapping
    public ResponseEntity <RespuestaMedicoDTO> registrar(@RequestBody @Valid DatosMedico datos, UriComponentsBuilder uriCom){
        Medico medico= repository.save( new Medico(datos));
        RespuestaMedicoDTO datosRespuesta = new RespuestaMedicoDTO(medico.getId(), medico.getNombre(), medico.getEmail(),
                        medico.getTelefono(), medico.getEspecialidad().toString(),
                        new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getNumero(),
                                medico.getDireccion().getComplemento(), medico.getDireccion().getBarrio(),
                                medico.getDireccion().getCiudad(), medico.getDireccion().getCodigo_postal(), medico.getDireccion().getEstado()));


        //201, nuevo registro en la base de datos
        URI uri= uriCom.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(datosRespuesta);
    }

    @GetMapping
    //cuantos datos por pagina el size
    public ResponseEntity<Page<MedicoDTO>> listar(@PageableDefault(size=10, sort= {"nombre"} ) Pageable paginas){

        return ResponseEntity.ok(repository.findByActivoTrue(paginas)
                .map(MedicoDTO::new));
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid ActualizarMedicoDTO datos){
        Medico medico = repository.getReferenceById(datos.id());
        medico.actualizar(datos);
        return ResponseEntity.ok(new RespuestaMedicoDTO(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento(), medico.getDireccion().getBarrio(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getCodigo_postal(), medico.getDireccion().getEstado())));

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        //repository.deleteById(id);
        //eliminacion logica
        Medico medico = repository.getReferenceById(id);
        medico.desactivarMedico();
        //devuelve 204
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaMedicoDTO> buscarMedico (@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        var datos= new RespuestaMedicoDTO(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento(), medico.getDireccion().getBarrio(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getCodigo_postal(), medico.getDireccion().getEstado()));


        return ResponseEntity.ok(datos);
    }
}
