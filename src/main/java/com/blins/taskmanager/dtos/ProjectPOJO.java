package com.blins.taskmanager.dtos;

import lombok.*;

@Data
@Builder
public class ProjectPOJO {
    private String name;
    private String description;

    public ProjectPOJO(String validProject, String s) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
