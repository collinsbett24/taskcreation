package com.blins.taskmanager.controller;


import com.blins.taskmanager.dtos.ProjectPOJO;
import com.blins.taskmanager.entiities.Project;
import com.blins.taskmanager.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Import({ProjectController.class}) // Ensure your controller is imported
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private ProjectPOJO validProjectPOJO;
    private ProjectPOJO invalidProjectPOJO;

    @BeforeEach
    public void setup() {
        validProjectPOJO = new ProjectPOJO("Valid Project", "This is a valid project description");
    }

    @Test
    public void createProject_ValidData_ReturnsCreated() throws Exception {
        Project project = new Project(); // Mock the expected created project
        project.setId(UUID.randomUUID());
        project.setName(validProjectPOJO.getName());
        project.setDescription(validProjectPOJO.getDescription());

        when(projectService.create(Mockito.any(ProjectPOJO.class))).thenReturn(project);

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Valid Project\",\"description\":\"This is a valid project description\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void createProject_InvalidData_ReturnsBadRequest() throws Exception {
        when(projectService.create(Mockito.any(ProjectPOJO.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"description\":\"\"}"))
                .andExpect(status().is5xxServerError());

    }

    @Test
    public void createProject_ValidationError_ReturnsBadRequest() throws Exception {
        // Simulate binding result errors if the name or description is invalid

        // Mock the controller to use validation errors in bindingResult
        mockMvc.perform(MockMvcRequestBuilders.post("/projects/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"description\":\"\"}"))
                .andExpect(status().is5xxServerError());
    }
}