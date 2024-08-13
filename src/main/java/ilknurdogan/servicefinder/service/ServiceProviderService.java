package ilknurdogan.servicefinder.service;


import ilknurdogan.servicefinder.dto.responseDto.ServiceProviderGetDto;
import ilknurdogan.servicefinder.entities.ServiceProvider;
import ilknurdogan.servicefinder.exception.InternalServerErrorException;
import ilknurdogan.servicefinder.exception.NotFoundException;
import ilknurdogan.servicefinder.repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepository;
    private final ModelMapper modelMapper;

    //GET ALL
    public List<ServiceProviderGetDto> getAll() {
        try {
            List<ServiceProvider> serviceProviders = serviceProviderRepository.findAll();

            return  serviceProviders.stream()
                    .map(provider -> modelMapper.map(provider, ServiceProviderGetDto.class))
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new InternalServerErrorException("Service provider could not be fetched.");
        }

    }

    // GET BY ID
    public ServiceProviderGetDto getServiceProviderById(Long id) {
            Optional<ServiceProvider> serviceProviderOptional = serviceProviderRepository.findById(id);
            if(serviceProviderOptional.isEmpty()){
                throw new NotFoundException("Service provider not found!");
            }
                return modelMapper.map(serviceProviderOptional.get(), ServiceProviderGetDto.class);

    }


    //DELETE BY ID
    public void deleteServiceProviderById(Long id){
        if(serviceProviderRepository.existsById(id)){
            try {
                serviceProviderRepository.deleteById(id);
            }catch (Exception e){
                throw new InternalServerErrorException("Service provider could not be deleted.");
            }
        }else{
            throw new NotFoundException("Service provider not found");
        }


    }
}
