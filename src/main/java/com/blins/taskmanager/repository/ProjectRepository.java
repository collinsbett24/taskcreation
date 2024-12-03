package com.blins.taskmanager.repository;

import com.blins.taskmanager.entiities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, UUID> {

}
