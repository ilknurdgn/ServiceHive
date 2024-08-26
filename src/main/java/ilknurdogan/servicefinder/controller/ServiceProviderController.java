package ilknurdogan.servicefinder.controller;

import ilknurdogan.servicefinder.dto.responseDto.ServiceProviderGetDto;
import ilknurdogan.servicefinder.service.ServiceProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/filter")
    public ResponseEntity<List<ServiceProviderGetDto>> getServiceProviderFilter(
            @RequestParam(required = true) String category,
            @RequestParam(required = false) String city,
            @RequestParam(required = false)  String district){
        List<ServiceProviderGetDto> filteredProviders = serviceProviderService.getServiceProviderFilter(category, city, district);
        return ResponseEntity.ok(filteredProviders);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteServiceProviderById(@RequestParam Long id){
        serviceProviderService.deleteServiceProviderById(id);
        return new ResponseEntity<>("Service provider deleted", HttpStatus.OK);
    }




}
