package edu.bootcamp.tma.controller;


import edu.bootcamp.tma.dto.TaskDto;
import edu.bootcamp.tma.service.TaskService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/save-task")
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto taskDto) {
       return ResponseEntity.status(HttpStatus.CREATED).
               body(taskService.save(taskDto));
    }

    @GetMapping("/get-all-tasks")
    public List<TaskDto> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/get-count")
    public Long getCount(){
       return taskService.getCount();
    }

    @PutMapping("/update-all")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.updateTask(taskDto));
    }

    @PatchMapping("/update-title")
    public ResponseEntity<TaskDto> updateTitle(@RequestParam String oldTitle,@RequestParam String newTitle){
        return ResponseEntity.ok(taskService.updateTitle(oldTitle,newTitle));
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
