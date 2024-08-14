package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.dto.requestDto.ProjectCreateDto;
import ilknurdogan.servicefinder.entities.Project;
import ilknurdogan.servicefinder.entities.ServiceProvider;
import ilknurdogan.servicefinder.exception.InternalServerErrorException;
import ilknurdogan.servicefinder.exception.NotFoundException;
import ilknurdogan.servicefinder.repository.ProjectRepository;
import ilknurdogan.servicefinder.repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;
    private final ServiceProviderRepository serviceProviderRepository;

    public Project createProject(ProjectCreateDto projectCreateDto) {
       try {
            Project project = getProject(projectCreateDto);
            projectRepository.save(project);
            return project;
        }catch (Exception e){
           throw new InternalServerErrorException("Project could not be created!");
       }

    }

    public Project getProject(ProjectCreateDto projectCreateDto){
        String description = projectCreateDto.getDescription();
        List<String> projectImgUrl = projectCreateDto.getProjectImgUrl();
        Long serviceProviderId = projectCreateDto.getServiceProviderId();

        Optional<ServiceProvider> optionalServiceProvider = serviceProviderRepository.findById(serviceProviderId);

        if(optionalServiceProvider.isEmpty()){
            throw new NotFoundException("Service provider not found!");
        }

        ServiceProvider serviceProvider = optionalServiceProvider.get();

        return Project.builder().description(description).projectImgUrl(projectImgUrl).serviceProvider(serviceProvider).build();
    }
}
