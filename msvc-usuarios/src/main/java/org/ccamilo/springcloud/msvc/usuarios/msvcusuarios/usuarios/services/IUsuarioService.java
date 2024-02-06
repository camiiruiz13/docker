package org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.services;

import org.ccamilo.springcloud.msvc.generico.modelogenerico.RequestDTO;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IUsuarioService {

    ResponseEntity<ResponseDTO> consultarUsuarios();
    ResponseEntity<ResponseDTO> consultarPorIdUsuario(Long idUsuario);
    ResponseEntity<ResponseDTO> eliminarPorIdUsuario(Long idUsuario);
    ResponseEntity<ResponseDTO> guardarUsuario(RequestDTO entrada);

    ResponseEntity<ResponseDTO> actualizarUsuario(RequestDTO entrada,Long idUsuario);

}
