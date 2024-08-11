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

    public boolean createIndividualProvider(IndividualProviderCreateDto individualProviderCreateDto) {
        IndividualProvider individualProvider =modelMapper.map(individualProviderCreateDto, IndividualProvider.class);
        individualProviderRepository.save(individualProvider);
        return true;
    }
}
