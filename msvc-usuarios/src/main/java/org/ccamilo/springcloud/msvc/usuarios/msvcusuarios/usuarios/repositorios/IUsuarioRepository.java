package org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.repositorios;


import org.ccamilo.springcloud.msvc.usuarios.msvcusuarios.usuarios.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {



}