package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.dto.ActualizarPacienteDTO;
import med.voll.api.dto.DetallePacienteDTO;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.model.paciente.DatosPaciente;
import med.voll.api.model.paciente.Paciente;
import med.voll.api.model.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name ="bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void registrar(@RequestBody @Valid DatosPaciente datos) {
        repository.save(new Paciente(datos));
    }

    @GetMapping
    public Page<PacienteDTO> listar(@PageableDefault(page = 0, size = 10, sort = { "nombre" }) Pageable paginacion) {
        return repository.findAllByActivoTrue(paginacion).map(PacienteDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid ActualizarPacienteDTO datos) {
        var paciente = repository.getReferenceById(datos.id());
        paciente.atualizarInformacion(datos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.eliminar();
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPaciente (@PathVariable Long id){
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetallePacienteDTO(paciente));
    }
}
