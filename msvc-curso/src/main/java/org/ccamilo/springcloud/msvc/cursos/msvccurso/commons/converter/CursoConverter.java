package org.ccamilo.springcloud.msvc.cursos.msvccurso.commons.converter;

import org.ccamilo.springcloud.msvc.cursos.msvccurso.models.dto.CursoDTO;
import org.ccamilo.springcloud.msvc.cursos.msvccurso.models.entities.Curso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CursoConverter {

    public static List<CursoDTO> convertirCursoDTOListToCursoList(List<Curso> cursos){
        List<CursoDTO> jsonCursos = new ArrayList<>();
        cursos.forEach(curso -> {
            jsonCursos.add(
                  CursoDTO.builder()
                          .idCurso(curso.getId())
                          .nombre(curso.getNombre())
                          .usuarioAuditoria(curso.getUsuarioCrea())
                          .build()
            );
        });
        return jsonCursos;
    }

    public static CursoDTO convertirCursoToCursoDTO(Curso curso){
        return  CursoDTO.builder()
                .idCurso(curso.getId())
                .nombre(curso.getNombre())
                .usuarioAuditoria(curso.getUsuarioCrea())
                .build();
    }

    public static Curso convertirCursoDTOToCurso(CursoDTO cursoDTO) {
        return Curso.builder()
                .id(cursoDTO.getIdCurso())
                .nombre(cursoDTO.getNombre())
                .usuarioCrea(cursoDTO.getUsuarioAuditoria())
                .build();
    }

}
