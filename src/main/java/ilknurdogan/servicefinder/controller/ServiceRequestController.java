package ilknurdogan.servicefinder.controller;

import ilknurdogan.servicefinder.dto.requestDto.ServiceRequestCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.ServiceRequestGetDto;
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

    // GET BY ID
    @GetMapping("/getById")
    public ResponseEntity<ServiceRequestGetDto> getById(@RequestParam Long id){
        ServiceRequestGetDto serviceRequest = serviceRequestService.getById(id);
        return new ResponseEntity<>(serviceRequest,HttpStatus.OK);
    }

    // GET ALL BY SERVICE PROVIDER ID
    @GetMapping("/getAllByServiceProviderId")
    public ResponseEntity<List<ServiceRequestGetDto>> getAllByServiceProviderId(@RequestParam Long serviceProviderId){
        List<ServiceRequestGetDto> serviceRequest = serviceRequestService.getAllByServiceProviderId(serviceProviderId);
        return new ResponseEntity<>(serviceRequest, HttpStatus.OK);
    }

    // GET ALL BY CUSTOMER ID
    @GetMapping("/getAllByCustomerId")
    public ResponseEntity<List<ServiceRequestGetDto>> getAllByCustomerId(@RequestParam Long customerId){
        List<ServiceRequestGetDto> serviceRequest = serviceRequestService.getAllByCustomerId(customerId);
        return new ResponseEntity<>(serviceRequest, HttpStatus.OK);
    }


    // CANCEL SERVICE REQUEST
    @PutMapping("/cancel")
    public ResponseEntity<String> cancelServiceRequest(@RequestParam Long id){
        serviceRequestService.cancelServiceRequest(id);
        return new ResponseEntity<>("Service request canceled", HttpStatus.OK);
    }

    // DELETE SERVICE REQUEST
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteServiceRequest(@RequestParam Long id){
        serviceRequestService.deleteServiceRequest(id);
        return new ResponseEntity<>("Service request successfully deleted.", HttpStatus.OK);
    }



}
