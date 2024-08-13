package ilknurdogan.servicefinder.controllers;

import ilknurdogan.servicefinder.dto.requestDto.CompanyProviderCreateDto;
import ilknurdogan.servicefinder.service.CompanyProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/service-provider")
@RequiredArgsConstructor
@Validated
public class CompanyProviderController {
    private final CompanyProviderService companyProviderService;

    @PostMapping("/create-company-provider")
    public ResponseEntity<String> createIndividualProvider(@RequestBody @Valid CompanyProviderCreateDto companyProviderCreateDto){
        companyProviderService.createCompanyProvider(companyProviderCreateDto);
        return new ResponseEntity<>("Service provider successfully created", HttpStatus.CREATED);
    }
}
