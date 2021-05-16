package com.example.notebook.csrs.repositories;

import com.example.notebook.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для хранения состояния модели
 */
@Repository
public interface PersonWriteRepository extends CrudRepository<Person, Long> {
}
