package org.ccamilo.springcloud.msvc.generico.modelogenerico;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private static final long serialVersionUID = 2405172041950251807L;

    public String message;
    public Object objectResponse;
    public int statusCode;
}
