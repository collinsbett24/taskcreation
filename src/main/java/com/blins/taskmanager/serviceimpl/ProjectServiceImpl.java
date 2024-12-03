package com.blins.taskmanager.serviceimpl;

import com.blins.taskmanager.exceptionhandler.ResourceNotFoundException;
import com.blins.taskmanager.dtos.ProjectPOJO;
import com.blins.taskmanager.dtos.TasksPojo;
import com.blins.taskmanager.entiities.Project;
import com.blins.taskmanager.entiities.Tasks;
import com.blins.taskmanager.repository.ProjectRepository;
import com.blins.taskmanager.repository.TasksRepository;
import com.blins.taskmanager.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final TasksRepository tasksRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, TasksRepository tasksRepository) {
        this.projectRepository = projectRepository;
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Project create(ProjectPOJO projectPOJO) {
        Project project = new Project();
        project.setName(projectPOJO.getName());
        project.setDescription(projectPOJO.getDescription());
        return projectRepository.save(project);
    }

    @Override
    public List<Project> fetchProjects() {
        return List.of((Project) projectRepository.findAll());
    }

    @Override
    public Optional<Project> fetchProject(UUID uuid) {
        return projectRepository.findById(uuid);
    }

    @Override
    public Tasks createTask(TasksPojo tasksPojo) {
        Tasks tasks = new Tasks();
        tasks.setTitle(tasksPojo.getTitle());
        tasks.setDescription(tasksPojo.getDescription());
        tasks.setStatus(tasksPojo.getStatus());
        tasks.setDueDate(tasksPojo.getDueDate());
        Project project = projectRepository.findById(tasksPojo.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + tasksPojo.getProjectId()));
        tasks.setProjectId(project);
        return tasksRepository.save(tasks);
    }

    @Override
    public Optional<Project> fetchTasksPerProject(UUID projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isPresent()){
            List<Tasks> tasks =  tasksRepository.findAllByProjectId(projectId);
            project.get().setTasks(tasks);
            return project;
        }
        throw new ResourceNotFoundException("Project not found" + projectId);
    }

    @Override
    public Tasks updateTask(UUID taskId, TasksPojo pojo) {
        Optional<Tasks> tasksOptional = tasksRepository.findById(taskId);
        if (tasksOptional.isPresent()){
            Tasks task = tasksOptional.get();
            task.setTitle(pojo.getTitle());
            task.setDescription(pojo.getDescription());
            task.setStatus(pojo.getStatus());
            task.setDueDate(pojo.getDueDate());
            return tasksRepository.saveAndFlush(task);
        } else {
            throw new ResourceNotFoundException("Task not found" + taskId);
        }

    }

    @Override
    public ResponseEntity<?> deleteTask(UUID taskId) {
        return tasksRepository.delete(taskId);
    }
}
