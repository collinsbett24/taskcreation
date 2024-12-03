package com.blins.taskmanager.repository;

import com.blins.taskmanager.entiities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, UUID> {

    @Query(value = "Select * from tasks where project_id like %", nativeQuery = true)
    List<Tasks> findAllByProjectId(UUID projectId);

    ResponseEntity<?> delete(UUID taskId);
}
