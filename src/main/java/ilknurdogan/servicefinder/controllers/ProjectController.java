package ilknurdogan.servicefinder.controllers;

import ilknurdogan.servicefinder.dto.requestDto.ProjectCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.ServiceProviderGetDto;
import ilknurdogan.servicefinder.entities.Project;
import ilknurdogan.servicefinder.entities.ServiceProvider;
import ilknurdogan.servicefinder.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateDto projectCreateDto){
         Project project = projectService.createProject(projectCreateDto);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

}
