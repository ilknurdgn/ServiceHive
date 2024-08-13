package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.dto.requestDto.CompanyProviderCreateDto;
import ilknurdogan.servicefinder.entities.CompanyProvider;
import ilknurdogan.servicefinder.exception.UniqueEmailException;
import ilknurdogan.servicefinder.repository.CompanyProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
@RequiredArgsConstructor
public class CompanyProviderService {

    private final CompanyProviderRepository companyProviderRepository;
    private final ModelMapper modelMapper;


    public void createCompanyProvider(CompanyProviderCreateDto companyProviderCreateDto) {
        if (companyProviderRepository.existsByEmail(companyProviderCreateDto.getEmail())) {
            throw new UniqueEmailException("Email already in use");
        }
        CompanyProvider companyProvider = modelMapper.map(companyProviderCreateDto, CompanyProvider.class);
        try{
            companyProviderRepository.save(companyProvider);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }
}
