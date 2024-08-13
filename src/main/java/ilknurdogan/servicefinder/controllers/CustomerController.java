package ilknurdogan.servicefinder.controllers;

import ilknurdogan.servicefinder.dto.requestDto.CustomerCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.CustomerGetDto;
import ilknurdogan.servicefinder.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final CustomerService customerService;

    //CREATE
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerCreateDto customerCreateDto){
        customerService.createCustomer(customerCreateDto);
        return new ResponseEntity<>("Customer successfully created.", HttpStatus.CREATED);
    }

    //GET ALL
    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerGetDto>> getAllCustomer(){
        List<CustomerGetDto> customers = customerService.getAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    //GET BY ID
    @GetMapping("/getById")
    public ResponseEntity<CustomerGetDto> getCustomerById(@RequestParam Long id){
        CustomerGetDto customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
