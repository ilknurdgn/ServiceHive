package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.dto.requestDto.IndividualProviderCreateDto;
import ilknurdogan.servicefinder.entities.IndividualProvider;
import ilknurdogan.servicefinder.exception.UniqueEmailException;
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

        if (individualProviderRepository.existsByEmail(individualProviderCreateDto.getEmail())) {
            throw new UniqueEmailException("Email already in use");
        }
        IndividualProvider individualProvider = modelMapper.map(individualProviderCreateDto, IndividualProvider.class);
        try{
            individualProviderRepository.save(individualProvider);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }
}
