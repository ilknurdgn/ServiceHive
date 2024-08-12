package ilknurdogan.servicefinder.controllers;


import ilknurdogan.servicefinder.dto.responseDto.ServiceProviderGetAllDto;
import ilknurdogan.servicefinder.service.ServiceProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/service-provider")
@RequiredArgsConstructor
public class ServiceProviderController {

    private final ServiceProviderService serviceProviderService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ServiceProviderGetAllDto>> getAll(){
        List<ServiceProviderGetAllDto>  providers = serviceProviderService.getAll();
        return  ResponseEntity.ok(providers);
    }



}
