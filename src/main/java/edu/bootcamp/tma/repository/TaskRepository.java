package edu.bootcamp.tma.repository;

import edu.bootcamp.tma.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskEntity, Integer> {
}
