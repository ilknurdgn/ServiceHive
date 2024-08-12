package ilknurdogan.servicefinder.service;


import ilknurdogan.servicefinder.dto.responseDto.ServiceProviderGetDto;
import ilknurdogan.servicefinder.entities.ServiceProvider;
import ilknurdogan.servicefinder.repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepository;
    private final ModelMapper modelMapper;

    public List<ServiceProviderGetDto> getAll() {
        try {
            List<ServiceProvider> serviceProviders = serviceProviderRepository.findAll();

            return  serviceProviders.stream()
                    .map(provider -> modelMapper.map(provider, ServiceProviderGetDto.class))
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new RuntimeException("Service provider could not be fetched.", e);
        }



    }

    public ServiceProviderGetDto getServiceProviderById(Long id) {
            Optional<ServiceProvider> serviceProviderOptional = serviceProviderRepository.findById(id);
            if (serviceProviderOptional.isPresent()) {
                return modelMapper.map(serviceProviderOptional.get(), ServiceProviderGetDto.class);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service provider not found");
            }

    }
}
