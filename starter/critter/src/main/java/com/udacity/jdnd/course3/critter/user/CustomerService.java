package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO saveCustomer(CustomerDTO dto){
        Customer c = AppMapper.INSTANCE.toCustomer(dto);
        Customer saved = customerRepository.save(c);
        return AppMapper.INSTANCE.toCustomerDTO(saved);
    }

    public List<CustomerDTO> getCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return AppMapper.INSTANCE.toCustomerDTOList(customers);
    }


}
