package edu.bootcamp.tma.service;

import edu.bootcamp.tma.dto.TaskDto;


import java.util.List;

public interface TaskService {
    TaskDto save(TaskDto taskDto);
    List<TaskDto> getAllTasks();
    Long getCount();
    TaskDto updateTask(TaskDto taskDto);
    void deleteTask(int id);

    void deleteTaskIds(List<Integer> ids);

    TaskDto updateTitle(String oldTitle,String newTitle);
}
