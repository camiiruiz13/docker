package org.ccamilo.springcloud.msvc.cursos.msvccurso.services;

import org.ccamilo.springcloud.msvc.cursos.msvccurso.commons.converter.CursoConverter;
import org.ccamilo.springcloud.msvc.cursos.msvccurso.models.dto.CursoDTO;
import org.ccamilo.springcloud.msvc.cursos.msvccurso.models.entities.Curso;
import org.ccamilo.springcloud.msvc.cursos.msvccurso.repositories.ICursoRepository;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.RequestDTO;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CursoServiceImpl implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private CursoConverter cursoConverter;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseDTO> consultarCursos() {
        try {
            List<Curso> cursos = (List<Curso>) cursoRepository.findAll();

            List<CursoDTO> jsonCursos = cursoConverter.convertirCursoDTOListToCursoList(cursos);

            return new ResponseEntity<>(ResponseDTO.builder().message("Se consultan los cursos")
                    .objectResponse(jsonCursos)
                    .statusCode(HttpStatus.OK.value())
                    .build(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseDTO.builder().message("Error consultando los cursos:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseDTO> consultarPorIdCurso(Long idCurso) {
        try {
            Optional<Curso> cursoFindById = cursoRepository.findById(idCurso);

            if (cursoFindById.isPresent()) {
                Curso curso = cursoFindById.get();
                CursoDTO cursoDTO = cursoConverter.convertirCursoToCursoDTO(curso);
                return new ResponseEntity<>(ResponseDTO.builder().message("Se consultan el curso con ID: " + idCurso)
                        .objectResponse(cursoDTO)
                        .statusCode(HttpStatus.OK.value())
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ResponseDTO.builder().message("El curso con ID " + idCurso + " no existe")
                        .objectResponse(null).statusCode(HttpStatus.CONFLICT.value()).build(), HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseDTO.builder().message("Error consultando el curso:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDTO> elimnarPorIdCurso(Long idCurso) {
        try {
            Optional<Curso> cursoFindById = cursoRepository.findById(idCurso);

            if (cursoFindById.isPresent()) {
                cursoRepository.deleteById(idCurso);
                return new ResponseEntity<>(ResponseDTO.builder().message("Se elimina el curso con ID: " + idCurso)
                        .objectResponse(true)
                        .statusCode(HttpStatus.NO_CONTENT.value())
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ResponseDTO.builder().message("El curso con ID " + idCurso + " no existe")
                        .objectResponse(null).statusCode(HttpStatus.CONFLICT.value()).build(), HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseDTO.builder().message("Error consultando el curso:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> guardarCurso(RequestDTO entrada) {

        try {
            Map<String, Object> request = (Map<String, Object>) entrada.getRequest();
            Curso curso = new Curso();
            curso.setUsuarioCrea(entrada.getAuditoria().usuario);
            curso.setNombre((String) request.get("nombre"));
            cursoRepository.save(curso);
            //curso = cursoRepository.findById(curso.getId()).get();
            CursoDTO cursoDTO = cursoConverter.convertirCursoToCursoDTO(curso);

            return new ResponseEntity<>(ResponseDTO.builder().message("Se guardan los cursos")
                    .objectResponse(cursoDTO)
                    .statusCode(HttpStatus.CREATED.value())
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseDTO.builder().message("Error guardando el curso:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<ResponseDTO> actualizarCurso(RequestDTO entrada, Long idCurso) {
        try {
            Optional<Curso> cursoFindById = cursoRepository.findById(idCurso);

            if (cursoFindById.isPresent()) {
                Curso curso = cursoFindById.get();
                Map<String, Object> request = (Map<String, Object>) entrada.getRequest();
                curso.setUsuarioCrea(entrada.getAuditoria().usuario);
                curso.setNombre((String) request.get("nombre"));

                cursoRepository.save(curso);
                //curso = cursoRepository.findById(curso.getId()).get();
                CursoDTO cursoDTO = cursoConverter.convertirCursoToCursoDTO(curso);
                return new ResponseEntity<>(ResponseDTO.builder().message("Se actualiza el curso con ID: " + idCurso)
                        .objectResponse(cursoDTO)
                        .statusCode(HttpStatus.OK.value())
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ResponseDTO.builder().message("El curso con ID " + idCurso + " no existe")
                        .objectResponse(null).statusCode(HttpStatus.CONFLICT.value()).build(), HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseDTO.builder().message("Error consultando el curso:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
