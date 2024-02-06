package org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.commons.converter;

import org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.models.dto.UsuarioDTO;
import org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.models.entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    public static List<UsuarioDTO> convertirUsuarioDTOListToUsuarioList(List<Usuario> usuarios){
        List<UsuarioDTO> jsonUsuarios = new ArrayList<>();
        usuarios.forEach(usuario -> {
            jsonUsuarios.add(
                 UsuarioDTO.builder()
                         .idUsuario(usuario.getId())
                         .nombre(usuario.getNombre())
                         .correo(usuario.getEmail())
                         .clave(usuario.getPassword())
                         .build()
            );
        });

        return jsonUsuarios;
    }

    public static UsuarioDTO convertirUsuarioToUsuarioDTO(Usuario usuario){
        return  UsuarioDTO.builder()
                .idUsuario(usuario.getId())
                .nombre(usuario.getNombre())
                .correo(usuario.getEmail())
                .clave(usuario.getPassword())
                .build();
    }

    public static Usuario convertirUsuarioDTOToUsuario(UsuarioDTO usuarioDTO){

        return Usuario.builder()
                .id(usuarioDTO.getIdUsuario())
                .nombre(usuarioDTO.getNombre())
                .email(usuarioDTO.getCorreo())
                .password(usuarioDTO.getClave())
                .build();
    }
}
