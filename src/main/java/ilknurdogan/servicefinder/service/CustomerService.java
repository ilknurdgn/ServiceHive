package ilknurdogan.servicefinder.service;

import ilknurdogan.servicefinder.dto.requestDto.CustomerCreateDto;
import ilknurdogan.servicefinder.dto.responseDto.CustomerGetDto;
import ilknurdogan.servicefinder.entity.Customer;
import ilknurdogan.servicefinder.exception.InternalServerErrorException;
import ilknurdogan.servicefinder.exception.NotFoundException;
import ilknurdogan.servicefinder.exception.UniqueEmailException;
import ilknurdogan.servicefinder.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    // CREATE
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

    // GET BY ID
    public CustomerGetDto getCustomerById(Long id) {
        try {
            Optional<Customer> optionalCustomer = customerRepository.findById(id);

            if(optionalCustomer.isEmpty()){
                throw new NotFoundException("Customer not found!");
            }
            return modelMapper.map(optionalCustomer, CustomerGetDto.class);
        }catch (Exception e){
            throw new InternalServerErrorException("Customer could not be fetched.");
        }
    }

    // DELETE
    public void deleteCustomerById(Long id) {
        if(customerRepository.existsById(id)){
            try {
                customerRepository.deleteById(id);
            }catch (Exception e){
                throw new InternalServerErrorException("Service provider could not be deleted.");
            }
        }else{
            throw new NotFoundException("Customer not found");
        }

    }
}
