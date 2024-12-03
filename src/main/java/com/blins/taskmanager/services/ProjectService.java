package com.blins.taskmanager.services;

import com.blins.taskmanager.dtos.ProjectPOJO;
import com.blins.taskmanager.dtos.TasksPojo;
import com.blins.taskmanager.entiities.Project;
import com.blins.taskmanager.entiities.Tasks;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectService {
    public Project create(ProjectPOJO projectPOJO);

    List<Project> fetchProjects();

    Optional<Project> fetchProject(UUID uuid);

    Tasks createTask(TasksPojo tasksPojo);

    Optional<Project> fetchTasksPerProject(UUID projectId);

    Tasks updateTask(UUID taskId, TasksPojo pojo);

    ResponseEntity<?> deleteTask(UUID taskId);
}
