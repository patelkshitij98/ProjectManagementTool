package com.example.project.demo.services;

import com.example.project.demo.exceptions.ProjectIdException;
import com.example.project.demo.models.Project;
import com.example.project.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        //Logic
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier() + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectIdentifier){
        Project project = projectRepository.findProjectByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project ID '" + projectIdentifier + "' doesn't exist");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectIdentifier){
        Project project = projectRepository.findProjectByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project cannot be deleted as project ID '" + projectIdentifier + "' doesn't exist");
        }
        projectRepository.delete(project);
    }

//    public void updateProjectByIdentifier(Project project, String projectIdentifier){
//        Project project1 = projectRepository.findProjectByProjectIdentifier(projectIdentifier.toUpperCase());
//        if(project1 == null){
//            throw new ProjectIdException("Project cannot be updated as project ID '" + projectIdentifier + "' doesn't exist");
//        }
//        projectRepository.delete(project1);
//        project.setProjectIdentifier(projectIdentifier.toUpperCase());
//        projectRepository.save(project);
//    }
}
