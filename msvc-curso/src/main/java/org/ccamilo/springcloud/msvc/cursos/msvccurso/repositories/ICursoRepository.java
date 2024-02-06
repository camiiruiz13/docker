package org.ccamilo.springcloud.msvc.cursos.msvccurso.repositories;

import org.ccamilo.springcloud.msvc.cursos.msvccurso.models.entities.Curso;
import org.springframework.data.repository.CrudRepository;

public interface ICursoRepository extends CrudRepository<Curso, Long> {
}