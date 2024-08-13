package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.dto.requestDto.CustomerCreateDto;
import ilknurdogan.servicefinder.entities.Customer;
import ilknurdogan.servicefinder.exception.UniqueEmailException;
import ilknurdogan.servicefinder.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

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
}
