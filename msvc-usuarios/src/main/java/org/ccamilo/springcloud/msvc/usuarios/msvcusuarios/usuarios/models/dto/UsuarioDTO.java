package org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.models.entities.Usuario}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO  {

    private Long idUsuario;
    private String nombre;
    @NotEmpty
    @Email
    private String correo;
    @NotEmpty
    private String clave;
}