package edu.bootcamp.tma.repository;

import edu.bootcamp.tma.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Integer> {
      Optional<TaskEntity> findByTitle(String title);

}
