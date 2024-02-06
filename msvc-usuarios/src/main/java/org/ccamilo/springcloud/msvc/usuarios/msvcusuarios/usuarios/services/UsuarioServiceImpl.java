package org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.services;

import org.ccamilo.springcloud.msvc.generico.modelogenerico.RequestDTO;
import org.ccamilo.springcloud.msvc.generico.modelogenerico.ResponseDTO;
import org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.commons.converter.UsuarioConverter;
import org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.models.dto.UsuarioDTO;
import org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.models.entities.Usuario;
import org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.repositorios.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConverter usuarioConverter;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseDTO> consultarUsuarios() {
        try {
            List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();

            List<UsuarioDTO> jsonUsuarios = usuarioConverter.convertirUsuarioDTOListToUsuarioList(usuarios);

            return new ResponseEntity<>(ResponseDTO.builder().message("Se consultan los usuarios")
                    .objectResponse(jsonUsuarios)
                    .statusCode(HttpStatus.OK.value())
                    .build(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseDTO.builder().message("Error consultando los usuarios:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseDTO> consultarPorIdUsuario(Long idUsuario) {

        try {
            Optional<Usuario> usuarioFindById = usuarioRepository.findById(idUsuario);

            if (usuarioFindById.isPresent()) {
                Usuario usuario = usuarioFindById.get();
                UsuarioDTO usuarioDTO = usuarioConverter.convertirUsuarioToUsuarioDTO(usuario);
                return new ResponseEntity<>(ResponseDTO.builder().message("Se consultan el  usuario con ID: " + idUsuario)
                        .objectResponse(usuarioDTO)
                        .statusCode(HttpStatus.OK.value())
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ResponseDTO.builder().message("El usuario con ID " + idUsuario + " no existe")
                        .objectResponse(null).statusCode(HttpStatus.CONFLICT.value()).build(), HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseDTO.builder().message("Error consultando el usuario:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDTO> eliminarPorIdUsuario(Long idUsuario) {
        try {
            Optional<Usuario> usuarioFindById = usuarioRepository.findById(idUsuario);

            if (usuarioFindById.isPresent()) {

                usuarioRepository.deleteById(idUsuario);
                return new ResponseEntity<>(ResponseDTO.builder().message("Se elimna el usuario con ID: " + idUsuario)
                        .objectResponse(true)
                        .statusCode(HttpStatus.NO_CONTENT.value())
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ResponseDTO.builder().message("El usuario con ID " + idUsuario + " no existe")
                        .objectResponse(null).statusCode(HttpStatus.CONFLICT.value()).build(), HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseDTO.builder().message("Error elimnando el usuarios:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> guardarUsuario(RequestDTO entrada) {
       try{
           Map<String, Object> request = (Map<String, Object>) entrada.getRequest();

           Usuario usuario = new Usuario();
           usuario.setNombre((String) request.get("nombre"));
           usuario.setPassword((String) request.get("clave"));
           usuario.setEmail(entrada.getAuditoria().getUsuario());
           usuarioRepository.save(usuario);
           //usuario = usuarioRepository.findById(usuario.getId()).get();
           UsuarioDTO usuarioDTO = usuarioConverter.convertirUsuarioToUsuarioDTO(usuario);

           return new ResponseEntity<>(ResponseDTO.builder().message("Se guardan los usuarios")
                   .objectResponse(usuarioDTO)
                   .statusCode(HttpStatus.CREATED.value())
                   .build(), HttpStatus.OK);


       }
       catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<>(ResponseDTO.builder().message("Error guardado el usuario:  " + e.getMessage())
                   .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
       }
    }

    @Override
    public ResponseEntity<ResponseDTO> actualizarUsuario(RequestDTO entrada, Long idUsuario) {
        try {
            Optional<Usuario> usuarioFindById = usuarioRepository.findById(idUsuario);

            if (usuarioFindById.isPresent()) {
                Usuario usuario = usuarioFindById.get();
                Map<String, Object> request = (Map<String, Object>) entrada.getRequest();

                usuario.setNombre((String) request.get("nombre"));
                usuario.setPassword((String) request.get("clave"));
                usuario.setEmail(entrada.getAuditoria().getUsuario());
                usuarioRepository.save(usuario);
                //usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario()).get();
               UsuarioDTO usuarioDTO = usuarioConverter.convertirUsuarioToUsuarioDTO(usuario);
                return new ResponseEntity<>(ResponseDTO.builder().message("Se actualiza el usuario con el ID: " + idUsuario)
                        .objectResponse(usuarioDTO)
                        .statusCode(HttpStatus.OK.value())
                        .build(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ResponseDTO.builder().message("El usuario con ID " + idUsuario + " no existe")
                        .objectResponse(null).statusCode(HttpStatus.CONFLICT.value()).build(), HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseDTO.builder().message("Error consultando el usuario:  " + e.getMessage())
                    .objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
