package org.ccamilo.springcloud.msvc.cursos.msvccurso.services;

import org.ccamilo.springcloud.msvc.generico.modelogenerico.RequestDTO;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ICursoService {

    ResponseEntity<ResponseDTO> consultarCursos();
    ResponseEntity<ResponseDTO> consultarPorIdCurso(Long idCurso);
    ResponseEntity<ResponseDTO> elimnarPorIdCurso(Long idCurso);
    ResponseEntity<ResponseDTO> guardarCurso(RequestDTO entrada);

    ResponseEntity<ResponseDTO> actualizarCurso(RequestDTO entrada, Long idCurso);
}
