package edu.bootcamp.tma.controller;

import edu.bootcamp.tma.dto.TaskDto;
import edu.bootcamp.tma.entity.TaskEntity;
import edu.bootcamp.tma.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/save-task")
    public TaskDto saveTask(@RequestBody TaskDto taskDto) {
        return taskService.save(taskDto);
    }

    @GetMapping("/get-all-tasks")
    public List<TaskDto> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/get-count")
    public Long getCount(){
       return taskService.getCount();
    }

    @DeleteMapping("/delete-task/{id}")
    public void deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
    }

    @DeleteMapping("/delete-multiple-task")
    public void deleteTasksIds(@RequestBody List<Integer> ids) {
        taskService.deleteTaskIds(ids);
    }

}
