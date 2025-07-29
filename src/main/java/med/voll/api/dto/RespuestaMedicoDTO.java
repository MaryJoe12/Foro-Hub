package med.voll.api.dto;

import med.voll.api.model.direccion.DatosDireccion;

public record RespuestaMedicoDTO (Long id, String nombre, String email, String telefono, String documento,
                                  DatosDireccion direccion){
}
