package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.dto.CancelarConsultaDTO;
import med.voll.api.dto.DetalleConsultaDTO;
import med.voll.api.model.consulta.DatosConsulta;
import med.voll.api.model.consulta.ReservaConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name ="bearer-key")
public class ConsultaController {

    @Autowired
    private ReservaConsultas reserva;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosConsulta datos){

        var detalleConsulta =reserva.reservar(datos);

        return ResponseEntity.ok(detalleConsulta);
    }

}
