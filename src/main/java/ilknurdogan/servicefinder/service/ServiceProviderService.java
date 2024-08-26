package ilknurdogan.servicefinder.service;


import ilknurdogan.servicefinder.dto.responseDto.ServiceProviderGetDto;
import ilknurdogan.servicefinder.entity.ServiceProvider;
import ilknurdogan.servicefinder.exception.InternalServerErrorException;
import ilknurdogan.servicefinder.exception.NotFoundException;
import ilknurdogan.servicefinder.repository.ServiceProviderRepository;
import jakarta.transaction.Transactional;
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
        try{
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

    //GET FILTER BY CATEGORY, CITY, AND DISTRICT
    public List<ServiceProviderGetDto> getServiceProviderFilter(String category, String city, String district){
        try{

            List<ServiceProvider> filteredServiceProviders;

            if (category != null && city != null && district != null) {
                filteredServiceProviders = serviceProviderRepository.findByCategoryAndCityAndDistrict(category, city, district);
            } else if (category != null && city != null) {
                filteredServiceProviders = serviceProviderRepository.findByCategoryAndCity(category, city);
            } else if (category != null) {
                filteredServiceProviders = serviceProviderRepository.findByCategory(category);
            } else {
                filteredServiceProviders = serviceProviderRepository.findAll();
            }

            return filteredServiceProviders.stream()
                    .map(provider -> modelMapper.map(provider, ServiceProviderGetDto.class))
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new InternalServerErrorException("Service provider could not be fetched.");
        }

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


    // UPDATE AVERAGE SCORE AFTER COMMENT UPDATE
    @Transactional
    public void updateAverageScoreAfterCommentUpdate(Long id, double oldScore, int score) {
        ServiceProvider serviceProvider = serviceProviderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ServiceProvider not found"));

        double totalScore = serviceProvider.getAverageScore() * serviceProvider.getTotalComments();
        totalScore = totalScore - oldScore + score;

        double newAverageScore = totalScore / serviceProvider.getTotalComments();
        serviceProvider.setAverageScore(newAverageScore);

        serviceProviderRepository.save(serviceProvider);
    }

    // UPDATE AVERAGE SCORE AFTER COMMENT DELETE
    @Transactional
    public void updateAverageScoreAfterCommentDelete(Long serviceProviderId, int scoreToRemove) {
        ServiceProvider serviceProvider = serviceProviderRepository.findById(serviceProviderId)
                .orElseThrow(() -> new NotFoundException("ServiceProvider not found"));

        double totalComments = serviceProvider.getTotalComments();

        double totalScore = serviceProvider.getAverageScore() * totalComments;
        totalScore = totalScore - scoreToRemove;

        totalComments--;

        double newAverageScore = totalScore / totalComments;

        serviceProvider.setTotalComments(totalComments);
        serviceProvider.setAverageScore(newAverageScore);

        serviceProviderRepository.save(serviceProvider);
    }
}
