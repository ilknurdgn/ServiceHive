package ilknurdogan.servicefinder.service;


import ilknurdogan.servicefinder.dto.responseDto.ServiceProviderGetAllDto;
import ilknurdogan.servicefinder.entities.ServiceProvider;
import ilknurdogan.servicefinder.repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepository;
    private final ModelMapper modelMapper;

    public List<ServiceProviderGetAllDto> getAll() {
        List<ServiceProvider> serviceProviders = serviceProviderRepository.findAll();

        return  serviceProviders.stream()
                .map(provider -> modelMapper.map(provider, ServiceProviderGetAllDto.class))
                .collect(Collectors.toList());


    }
}
