package com.example.project.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Project Name required")
    private String projectName;
    @NotBlank(message = "Project identifier required")
    @Size(min = 4, max = 5, message = "Project Identifier permits only 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;
    @NotBlank(message = "Project description required")
    private String description;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updatedAt;

    @PrePersist
    private void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    private void onUpdate(){
        this.updatedAt = new Date();
    }

}
