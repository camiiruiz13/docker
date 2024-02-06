package org.ccamilo.springcloud.msvc.cursos.msvccurso.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link org.ccamilo.springcloud.msvc.cursos.msvccurso.models.entities.Curso}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDTO {
    private Long idCurso;
    private String nombre;
    private String usuarioAuditoria;
}