package edu.bootcamp.tma.service;

import edu.bootcamp.tma.dto.TaskDto;
import edu.bootcamp.tma.entity.TaskEntity;
import edu.bootcamp.tma.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskDto save(TaskDto taskDto){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskDto.getTitle());
        taskEntity.setDescription(taskDto.getDescription());

        TaskEntity savedTask = taskRepository.save(taskEntity);

        TaskDto dto = new TaskDto();
        dto.setId(savedTask.getId());
        dto.setTitle(savedTask.getTitle());
        dto.setDescription(savedTask.getDescription());

        return dto;
    }

    public List<TaskDto> getAllTasks() {

        Iterable<TaskEntity> allTasks = taskRepository.findAll();

        List<TaskDto> dtoList = new ArrayList<>();

        allTasks.forEach(saved ->{
            TaskDto dto = new TaskDto();

            dto.setId(saved.getId());
            dto.setTitle(saved.getTitle());
            dto.setDescription(saved.getDescription());

            dtoList.add(dto);
        });
        return dtoList;
    }

    public Long getCount() {
        return taskRepository.count();
    }

    public void deleteTask(int id) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(id);
        taskRepository.delete(taskEntity);
    }

    public void deleteTaskIds(List<Integer> ids) {
        taskRepository.deleteAllById(ids);
    }

}
