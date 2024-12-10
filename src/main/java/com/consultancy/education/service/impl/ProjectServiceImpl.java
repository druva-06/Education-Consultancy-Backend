package com.consultancy.education.service.impl;

import com.consultancy.education.DTOs.requestDTOs.project.ProjectRequestDto;
import com.consultancy.education.DTOs.responseDTOs.project.ProjectResponseDto;
import com.consultancy.education.exception.NotFoundException;
import com.consultancy.education.model.Project;
import com.consultancy.education.model.Student;
import com.consultancy.education.repository.ProjectRepository;
import com.consultancy.education.repository.StudentRepository;
import com.consultancy.education.service.ProjectService;
import com.consultancy.education.transformer.ProjectTransformer;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final StudentRepository studentRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, StudentRepository studentRepository) {
        this.projectRepository = projectRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto, Long studentId) {
        if(studentRepository.findById(studentId).isPresent()){
            Student student = studentRepository.findById(studentId).get();
            Project project =  ProjectTransformer.toEntity(projectRequestDto);
            student.getProjects().add(project);
            project.setStudent(student);
            project = projectRepository.save(project);
            return ProjectTransformer.toDTO(project, student);
        }
        throw new NotFoundException("Student not found");
    }

    @Override
    public ProjectResponseDto getProject(Long id) {
        if(projectRepository.findById(id).isPresent()){
            Project project = projectRepository.findById(id).get();
            Student student = project.getStudent();
            return ProjectTransformer.toDTO(project, student);
        }
        throw new NotFoundException("Project not found");
    }

    @Override
    public List<ProjectResponseDto> getProjects(Long studentId) {
        if(studentRepository.findById(studentId).isPresent()){
            Student student = studentRepository.findById(studentId).get();
            List<Project> projects = student.getProjects();
            return ProjectTransformer.toDTO(projects, student);
        }
        throw new NotFoundException("Student not found");
    }

    @Override
    public ProjectResponseDto updateProject(ProjectRequestDto projectRequestDto, Long id) {
        if(projectRepository.findById(id).isPresent()){
            Project project = projectRepository.findById(id).get();
            ProjectTransformer.updateProject(project, projectRequestDto);
            project = projectRepository.save(project);
            return ProjectTransformer.toDTO(project, project.getStudent());
        }
        throw new NotFoundException("Project not found");
    }

    @Override
    public ProjectResponseDto deleteProject(Long id) {
        if(projectRepository.findById(id).isPresent()){
            Project project = projectRepository.findById(id).get();
            Student student = project.getStudent();
            projectRepository.deleteById(id);
            return ProjectTransformer.toDTO(project, student);
        }
        throw new NotFoundException("Project not found");
    }
}
