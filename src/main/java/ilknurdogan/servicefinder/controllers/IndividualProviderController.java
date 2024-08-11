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
    public ResponseEntity<String> createIndividualProvider(@RequestBody IndividualProviderCreateDto individualProviderCreateDto){
        try {
            individualProviderService.createIndividualProvider(individualProviderCreateDto);
            return new ResponseEntity<>("Service provider successfully created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
