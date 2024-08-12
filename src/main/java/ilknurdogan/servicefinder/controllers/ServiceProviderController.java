package ilknurdogan.servicefinder.controllers;


import ilknurdogan.servicefinder.dto.responseDto.ServiceProviderGetDto;
import ilknurdogan.servicefinder.service.ServiceProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/service-provider")
@RequiredArgsConstructor
public class ServiceProviderController {

    private final ServiceProviderService serviceProviderService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ServiceProviderGetDto>> getAll(){
        List<ServiceProviderGetDto> providers = serviceProviderService.getAll();
        return  ResponseEntity.ok(providers);
    }

    @GetMapping("/getById")
    public ResponseEntity<ServiceProviderGetDto> getServiceProviderById(@RequestParam Long id){
        ServiceProviderGetDto provider = serviceProviderService.getServiceProviderById(id);
        return new ResponseEntity<>(provider, HttpStatus.OK);
    }



}
