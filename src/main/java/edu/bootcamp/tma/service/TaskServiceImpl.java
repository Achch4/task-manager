package edu.bootcamp.tma.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bootcamp.tma.dto.TaskDto;
import edu.bootcamp.tma.entity.TaskEntity;
import edu.bootcamp.tma.exception.TaskAlreadyExist;
import edu.bootcamp.tma.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        TaskEntity taskEntity = mapper.convertValue
                (taskDto, TaskEntity.class);
        return mapper.convertValue(taskRepository.save(taskEntity), TaskDto.class);
    }

    @Override
    public TaskDto updateTitle(String oldTitle,String newTitle) {

        Optional<TaskEntity> byDesc = taskRepository.findByTitle(oldTitle);
        if (byDesc.isEmpty()){
            throw new TaskAlreadyExist("task already exist with title " + oldTitle);
        }

        TaskEntity taskEntity = byDesc.get();
        taskEntity.setTitle(newTitle);


        return mapper.convertValue(taskRepository.save(taskEntity), TaskDto.class);

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
