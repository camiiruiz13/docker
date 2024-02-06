package org.ccamilo.springcloud.msvc.cursos.msvccurso.controller;

import org.ccamilo.springcloud.msvc.cursos.msvccurso.services.ICursoService;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.RequestDTO;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CursoControllerImpl implements ICursoController {

    @Autowired
    private ICursoService cursoService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseDTO> consultarCursos() {
        return cursoService.consultarCursos();
    }

    @Override
    @GetMapping("/{idCurso}")
    public ResponseEntity<ResponseDTO> consultarPorIdCurso(@PathVariable Long idCurso) {
        return cursoService.consultarPorIdCurso(idCurso);
    }

    @Override
    @DeleteMapping("/{idCurso}")
    public ResponseEntity<ResponseDTO> elimnarPorIdCurso(@PathVariable Long idCurso) {
        return cursoService.elimnarPorIdCurso(idCurso);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseDTO> guardarCurso(@RequestBody RequestDTO entrada, BindingResult result) {
        if (result.hasErrors()) {
            return validarCamposUsuario(result);
        }
        return cursoService.guardarCurso(entrada);
    }

    @Override
    @PutMapping("/{idCurso}")
    public ResponseEntity<ResponseDTO> actualizarCurso(RequestDTO entrada, BindingResult result, Long idCurso) {
        if (result.hasErrors()) {
            return validarCamposUsuario(result);
        }
        return cursoService.actualizarCurso(entrada, idCurso);
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
