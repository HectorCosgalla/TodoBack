package com.valcos98.todolist.taskcomponents;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "tasks")
@NoArgsConstructor
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "isDone")
    private Boolean isDone;

    @JsonCreator
    public TaskModel(@JsonProperty("title") String title){
        this.title = title;
        this.isDone = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }
}
