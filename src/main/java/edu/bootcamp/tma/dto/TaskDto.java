package edu.bootcamp.tma.dto;

import lombok.Data;


@Data
public class TaskDto {
    private Integer id;

    private String title;

    private String description;

    private Byte timeInHours;
}
