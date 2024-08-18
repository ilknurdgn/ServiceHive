package ilknurdogan.servicefinder.controllers;

import ilknurdogan.servicefinder.dto.requestDto.ServiceRequestCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.ServiceRequestGetDto;
import ilknurdogan.servicefinder.entities.ServiceRequest;
import ilknurdogan.servicefinder.service.ServiceRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-request")
@RequiredArgsConstructor
@Validated
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<String> createServiceRequest(@RequestBody @Valid ServiceRequestCreateDto serviceRequestCreateDto) {
        serviceRequestService.createServiceRequest(serviceRequestCreateDto);
        return new ResponseEntity<>("Service request successfully created.", HttpStatus.CREATED);
    }

}
