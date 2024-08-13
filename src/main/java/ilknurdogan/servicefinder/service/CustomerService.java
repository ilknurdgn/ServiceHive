package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.dto.requestDto.CustomerCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.CustomerGetDto;
import ilknurdogan.servicefinder.entities.Customer;
import ilknurdogan.servicefinder.exception.InternalServerErrorException;
import ilknurdogan.servicefinder.exception.UniqueEmailException;
import ilknurdogan.servicefinder.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    //CREATE
    public void createCustomer(CustomerCreateDto customerCreateDto) {

        if(customerRepository.existsByEmail(customerCreateDto.getEmail())){
            throw new UniqueEmailException("Email already in use");
        }
        try {
            Customer customer = modelMapper.map(customerCreateDto, Customer.class);
            customerRepository.save(customer);

        }catch(Exception e){
            throw new RuntimeException("Customer could not be created", e);
        }
    }

    // GET ALL
    public List<CustomerGetDto> getAllCustomer() {
        try {
            List<Customer> customerList = customerRepository.findAll();
            return customerList.stream()
                    .map(customer -> modelMapper.map(customer, CustomerGetDto.class))
                    .collect(Collectors.toList());
        }catch(Exception e){
            throw new InternalServerErrorException("Customer could not be fetched.");
        }

    }
}
