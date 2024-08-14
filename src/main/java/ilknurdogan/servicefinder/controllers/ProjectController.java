package ilknurdogan.servicefinder.controllers;

import ilknurdogan.servicefinder.dto.requestDto.ProjectCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.ProjectGetDto;
import ilknurdogan.servicefinder.entities.Project;
import ilknurdogan.servicefinder.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // GET ALL PROJECT BY SERVICE PROVIDER ID
    @GetMapping("/getAllProjectByServiceProviderById")
    public ResponseEntity<List<ProjectGetDto>> getAllProjectByServiceProviderId (@RequestParam Long serviceProviderId){
        List<ProjectGetDto> projectList = projectService.getAllProjectByServiceProviderId(serviceProviderId);
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

}
