package com.udacity.jdnd.course3.critter.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AppMapper {
    AppMapper INSTANCE = Mappers.getMapper(AppMapper.class);

    @Mapping(target = "pet", ignore = true)
    Customer toCustomer(CustomerDTO dto);

    @Mapping(target = "petIds", ignore = true)
    CustomerDTO toCustomerDTO(Customer c);

    List<CustomerDTO> toCustomerDTOList(List<Customer> customers);
}
