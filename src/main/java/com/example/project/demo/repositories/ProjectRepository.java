package com.example.project.demo.repositories;

import com.example.project.demo.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findProjectByProjectIdentifier(String projectIdentifier);
}
