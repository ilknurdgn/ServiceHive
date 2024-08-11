package ilknurdogan.servicefinder.controllers;

import ilknurdogan.servicefinder.dto.requestDto.IndividualProviderCreateDto;
import ilknurdogan.servicefinder.service.IndividualProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/service-provider")
@RequiredArgsConstructor
public class IndividualProviderController {

    private final IndividualProviderService individualProviderService;

    @PostMapping("/create-individual-provider")
    public ResponseEntity<Boolean> createIndividualProvider(@RequestBody IndividualProviderCreateDto individualProviderCreateDto){
        Boolean createIndividualProvider = individualProviderService.createIndividualProvider(individualProviderCreateDto);
        return new ResponseEntity<>(createIndividualProvider, HttpStatus.CREATED);
    }
}
