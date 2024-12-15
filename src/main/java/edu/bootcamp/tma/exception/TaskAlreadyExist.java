package edu.bootcamp.tma.exception;

public class TaskAlreadyExist extends RuntimeException{
    public TaskAlreadyExist(String message){
        super(message);

    }
}
