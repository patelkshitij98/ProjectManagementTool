package com.example.project.demo.controllers;

import com.example.project.demo.models.Project;
import com.example.project.demo.services.ConstraintValidatorService;
import com.example.project.demo.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ConstraintValidatorService constraintValidatorService;

    @PostMapping("")
    private ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = constraintValidatorService.checkConstraints(result);
        if (errorMap != null) return errorMap;
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    private ResponseEntity<?> findProjectByIdentifier(@PathVariable String projectIdentifier) {
        Project project = projectService.findProjectByIdentifier(projectIdentifier);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("")
    private Iterable<Project> findProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectIdentifier}")
    private ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIdentifier){
        projectService.deleteProjectByIdentifier(projectIdentifier);
        return new ResponseEntity<String>("Project with Identifier '" + projectIdentifier + "' deleted",HttpStatus.OK);
    }

//    @PutMapping("/{projectIdentifier}")
//    private ResponseEntity<?> updateProjectByIdentifier(@Valid @RequestBody Project project, @PathVariable String projectIdentifier){
//        projectService.updateProjectByIdentifier(project, projectIdentifier);
//        return new ResponseEntity<String>("Project with Identifier '" + projectIdentifier + "' updated",HttpStatus.OK);
//    }
}
