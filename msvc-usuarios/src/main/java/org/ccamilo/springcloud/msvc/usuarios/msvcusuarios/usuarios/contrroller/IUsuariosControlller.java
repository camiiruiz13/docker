package org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.contrroller;

import jakarta.validation.Valid;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.RequestDTO;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUsuariosControlller {

    ResponseEntity<ResponseDTO> consultarUsuarios();
    ResponseEntity<ResponseDTO> consultarPorIdUsuario(@PathVariable Long idUsuario);
    ResponseEntity<ResponseDTO> eliminarPorIdUsuario(@PathVariable Long idUsuario);
    ResponseEntity<ResponseDTO> guardarUsuario(@Valid @RequestBody  RequestDTO entrada, BindingResult result);
    ResponseEntity<ResponseDTO> actualizarUsuario(@Valid @RequestBody  RequestDTO entrada, BindingResult result, @PathVariable Long idUsuario);
}
