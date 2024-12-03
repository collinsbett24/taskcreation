package com.blins.taskmanager.entiities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Date dueDate;
    @ManyToOne
    @JoinColumn(name = "project_id") // Foreign key column in the 'tasks' table
    private Project project;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Project getProjectId() {
        return project;
    }

    public void setProjectId(Project projectId) {
        this.project = projectId;
    }



}
