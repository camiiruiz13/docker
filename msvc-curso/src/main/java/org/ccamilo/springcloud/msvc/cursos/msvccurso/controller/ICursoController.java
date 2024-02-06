package org.ccamilo.springcloud.msvc.cursos.msvccurso.controller;

import org.ccamilo.springcloud.msvc.generico.modelogenerico.RequestDTO;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICursoController {

    ResponseEntity<ResponseDTO> consultarCursos();
    ResponseEntity<ResponseDTO> consultarPorIdCurso(@PathVariable Long idCurso);
    ResponseEntity<ResponseDTO> elimnarPorIdCurso(@PathVariable Long idCurso);
    ResponseEntity<ResponseDTO> guardarCurso(@RequestBody RequestDTO entrada, BindingResult result);

    ResponseEntity<ResponseDTO> actualizarCurso(@RequestBody RequestDTO entrada, BindingResult result, @PathVariable Long idCurso);
}
