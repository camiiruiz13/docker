package org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.contrroller;

import jakarta.validation.Valid;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.RequestDTO;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.ResponseDTO;
import org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UsuarioControllerImpl implements IUsuariosControlller {

    @Autowired
    private IUsuarioService usuarioService;
    @Override
    @GetMapping
    public ResponseEntity<ResponseDTO> consultarUsuarios() {
        return usuarioService.consultarUsuarios();
    }

    @Override
    @GetMapping("/{idUsuario}")
    public ResponseEntity<ResponseDTO> consultarPorIdUsuario(@PathVariable Long idUsuario) {
        return usuarioService.consultarPorIdUsuario(idUsuario);
    }

    @Override
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<ResponseDTO> eliminarPorIdUsuario(@PathVariable Long idUsuario) {
        return usuarioService.eliminarPorIdUsuario(idUsuario);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseDTO> guardarUsuario(@Valid @RequestBody  RequestDTO entrada, BindingResult result) {
        if (result.hasErrors()) {
            return validarCamposUsuario(result);
        }
        return usuarioService.guardarUsuario(entrada);
    }


    @Override
    @PutMapping("/{idUsuario}")
    public ResponseEntity<ResponseDTO> actualizarUsuario(@RequestBody  RequestDTO entrada, BindingResult result, @PathVariable Long idUsuario) {
        if (result.hasErrors()) {
            return validarCamposUsuario(result);
        }
        return usuarioService.actualizarUsuario(entrada, idUsuario);
    }

    private static ResponseEntity<ResponseDTO> validarCamposUsuario(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "El campo " + error.getField() + " "
                    + error.getDefaultMessage());
        });
        return new ResponseEntity<>(ResponseDTO.builder()
                .message("Errores de validación en la solicitud")
                .objectResponse(errores)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.BAD_REQUEST);
    }

}
