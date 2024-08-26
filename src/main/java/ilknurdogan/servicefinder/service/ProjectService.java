package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.dto.requestDto.ProjectCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.ProjectGetDto;
import ilknurdogan.servicefinder.entity.Project;
import ilknurdogan.servicefinder.entity.ServiceProvider;
import ilknurdogan.servicefinder.exception.InternalServerErrorException;
import ilknurdogan.servicefinder.exception.NotFoundException;
import ilknurdogan.servicefinder.repository.ProjectRepository;
import ilknurdogan.servicefinder.repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;
    private final ServiceProviderRepository serviceProviderRepository;


    // CREATE
    public Project createProject(ProjectCreateDto projectCreateDto) {
        try {
            Project project = getProject(projectCreateDto);
            projectRepository.save(project);
            return project;
        } catch (Exception e) {
            throw new InternalServerErrorException("Project could not be created!");
        }

    }

    public Project getProject(ProjectCreateDto projectCreateDto) {
        String description = projectCreateDto.getDescription();
        List<String> projectImgUrl = projectCreateDto.getProjectImgUrl();
        Long serviceProviderId = projectCreateDto.getServiceProviderId();

        Optional<ServiceProvider> optionalServiceProvider = serviceProviderRepository.findById(serviceProviderId);

        if (optionalServiceProvider.isEmpty()) {
            throw new NotFoundException("Service provider not found!");
        }

        ServiceProvider serviceProvider = optionalServiceProvider.get();

        return Project.builder().description(description).projectImgUrl(projectImgUrl).serviceProvider(serviceProvider).build();
    }


    // GET ALL PROJECT BY SERVICE PROVIDER ID
    public List<ProjectGetDto> getAllProjectByServiceProviderId(Long serviceProviderId) {
        try {
            List<Project> projects = projectRepository.findByServiceProvider_Id(serviceProviderId);

            return projects.stream()
                    .map(project -> modelMapper.map(project, ProjectGetDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Projects could not be fetched.", e);
        }
    }

    // GET BY PROJECT ID
    public ProjectGetDto getByProjectId(Long id) {
        try {
            Optional<Project> optionalProject = projectRepository.findById(id);
            if (optionalProject.isEmpty()) {
                throw new NotFoundException("Project not found!");
            }
            Project project = optionalProject.get();
            return modelMapper.map(project, ProjectGetDto.class);
        } catch (Exception e) {
            throw new InternalServerErrorException("Project could not be fetched.", e);
        }
    }

    // DELETE
    public void deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            try {
                projectRepository.deleteById(id);
            } catch (Exception e) {
                throw new InternalServerErrorException("Project could not be deleted!", e);
            }
        } else {
            throw new NotFoundException("Project not found!");
        }

    }
}