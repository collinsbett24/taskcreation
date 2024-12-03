package com.blins.taskmanager.controller;

import com.blins.taskmanager.dtos.ProjectPOJO;
import com.blins.taskmanager.dtos.TasksPojo;
import com.blins.taskmanager.entiities.Project;
import com.blins.taskmanager.entiities.Tasks;
import com.blins.taskmanager.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/")
public class ProjectController {


    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("projects/")
    ResponseEntity<?> createProject(@Valid @RequestBody ProjectPOJO projectPOJO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
       Project newProject =  projectService.create(projectPOJO);
       return  new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    @GetMapping("projects/")
    ResponseEntity<List<Project>> fetchProjects(){
        List<Project> projects =  projectService.fetchProjects();
        return  new ResponseEntity<>(projects, HttpStatus.OK);
    }
    @GetMapping("projects/{id}")
    ResponseEntity<Optional<Project>> fetchProject(@PathVariable UUID uuid){
        Optional<Project> project =  projectService.fetchProject(uuid);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("projects/{projectId}/tasks")
    ResponseEntity<?> createProject(@Valid @PathVariable UUID projectId, @RequestBody TasksPojo tasksPojo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        tasksPojo.setProjectId(projectId);
        Tasks newTask =  projectService.createTask(tasksPojo);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @GetMapping("projects/{projectId}/tasks")
    ResponseEntity<Optional<Project>> fetchTasksPerProjects(@PathVariable UUID projectId){
        Optional<Project> tasks =  projectService.fetchTasksPerProject(projectId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    @PutMapping("tasks/{taskId}")
    ResponseEntity<Tasks> updateTask(@PathVariable UUID taskId, @RequestBody TasksPojo pojo){
        Tasks tasks =  projectService.updateTask(taskId, pojo);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("tasks/{taskId}")
    ResponseEntity<?> deleteTasks(@PathVariable UUID taskId){
        return projectService.deleteTask(taskId);
    }


}
