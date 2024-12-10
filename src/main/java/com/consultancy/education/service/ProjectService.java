package com.consultancy.education.service;

import com.consultancy.education.DTOs.requestDTOs.project.ProjectRequestDto;
import com.consultancy.education.DTOs.responseDTOs.project.ProjectResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ProjectService {
    ProjectResponseDto addProject(ProjectRequestDto projectRequestDto, Long studentId);

    ProjectResponseDto getProject(Long id);

    List<ProjectResponseDto> getProjects(Long studentId);

    ProjectResponseDto updateProject(ProjectRequestDto projectRequestDto, Long id);

    ProjectResponseDto deleteProject(Long id);
}
