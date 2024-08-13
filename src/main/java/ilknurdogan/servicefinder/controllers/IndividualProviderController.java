package ilknurdogan.servicefinder.controllers;

import ilknurdogan.servicefinder.dto.requestDto.IndividualProviderCreateDto;
import ilknurdogan.servicefinder.service.IndividualProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/service-provider")
@RequiredArgsConstructor
@Validated
public class IndividualProviderController {

    private final IndividualProviderService individualProviderService;

    @PostMapping("/create-individual-provider")
    public ResponseEntity<String> createIndividualProvider(@RequestBody @Valid IndividualProviderCreateDto individualProviderCreateDto){
            individualProviderService.createIndividualProvider(individualProviderCreateDto);
            return new ResponseEntity<>("Service provider successfully created", HttpStatus.CREATED);
    }
    }

