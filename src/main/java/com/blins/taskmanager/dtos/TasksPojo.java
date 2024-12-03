package com.blins.taskmanager.dtos;

import com.blins.taskmanager.entiities.Status;
import lombok.*;


import java.util.Date;
import java.util.UUID;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TasksPojo {
    private String title;
    private String description;
    private Status status;
    private Date dueDate;
    private UUID projectId;
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

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }


}
