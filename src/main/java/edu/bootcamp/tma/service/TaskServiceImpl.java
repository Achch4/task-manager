package edu.bootcamp.tma.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp.tma.dto.TaskDto;
import edu.bootcamp.tma.entity.TaskEntity;
import edu.bootcamp.tma.exception.TaskAlreadyExist;
import edu.bootcamp.tma.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final ObjectMapper mapper;

    public TaskDto save(TaskDto taskDto){
        TaskEntity taskEntity = mapper.convertValue(taskDto, TaskEntity.class);

        Optional<TaskEntity> byTitle = taskRepository.findByTitle(taskDto.getTitle());

        if (byTitle.isPresent()){
            throw new TaskAlreadyExist("task already exist with title " + taskDto.getTitle());
        }

        TaskEntity savedTask = taskRepository.save(taskEntity);
        return mapper.convertValue(savedTask, TaskDto.class);
    }

    public List<TaskDto> getAllTasks() {
        Iterable<TaskEntity> allTasks = taskRepository.findAll();
        List<TaskDto> dtoList = new ArrayList<>();
        allTasks.forEach(task -> dtoList.add(mapper.convertValue(task, TaskDto.class)));
        return dtoList;
    }


    public Long getCount() {
        return taskRepository.count();
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        TaskEntity taskEntity = mapper.convertValue
                (taskDto, TaskEntity.class);
        return mapper.convertValue(taskRepository.save(taskEntity), TaskDto.class);
    }

    @Override
    public TaskDto updateTitle(String oldTitle,String newTitle) {

        Optional<TaskEntity> byDesc = taskRepository.findByTitle(oldTitle);
        if (byDesc.isEmpty()) {
            throw new IllegalArgumentException("Task with title '" + oldTitle + "' does not exist.");
        }


        TaskEntity taskEntity = byDesc.get();
        taskEntity.setTitle(newTitle);
        return mapper.convertValue(taskRepository.save(taskEntity), TaskDto.class);

    }

    @Override
    public void deleteTask(int id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task with ID " + id + " does not exist.");
        }
        taskRepository.deleteById(id);
    }



    public void deleteTaskIds(List<Integer> ids) {
        taskRepository.deleteAllById(ids);
    }




}
