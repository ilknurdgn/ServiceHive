package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.dto.requestDto.IndividualProviderCreateDto;
import ilknurdogan.servicefinder.entities.IndividualProvider;
import ilknurdogan.servicefinder.repository.IndividualProviderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualProviderService {

    private final ModelMapper modelMapper;
    private final IndividualProviderRepository individualProviderRepository;

    public void createIndividualProvider(IndividualProviderCreateDto individualProviderCreateDto) {
        try{
            IndividualProvider individualProvider = modelMapper.map(individualProviderCreateDto, IndividualProvider.class);
            individualProviderRepository.save(individualProvider);
        }catch(Exception e){
            throw new RuntimeException("Failed to create service provider", e);
        }

    }
}
