package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.dto.requestDto.CompanyProviderCreateDto;
import ilknurdogan.servicefinder.entities.CompanyProvider;
import ilknurdogan.servicefinder.repository.CompanyProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
@RequiredArgsConstructor
public class CompanyProviderService {

    private final CompanyProviderRepository companyProviderRepository;
    private final ModelMapper modelMapper;


    public Boolean createCompanyProvider(CompanyProviderCreateDto companyProviderCreateDto) {
        CompanyProvider companyProvider = modelMapper.map(companyProviderCreateDto, CompanyProvider.class);
        companyProviderRepository.save(companyProvider);
        return true;
    }
}
