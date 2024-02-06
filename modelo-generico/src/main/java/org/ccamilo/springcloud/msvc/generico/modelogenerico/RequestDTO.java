package org.ccamilo.springcloud.msvc.generico.modelogenerico;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDTO {

    private static final long serialVersionUID = 2405172041950251807L;

    public AuditoriaDTO auditoria;

    public Object request;
}
