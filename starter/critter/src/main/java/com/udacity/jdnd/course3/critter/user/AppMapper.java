package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = { PetMapper.class })
public interface AppMapper{

    AppMapper INSTANCE = Mappers.getMapper(AppMapper.class);

    @Mapping(source = "pet", target = "petIds", qualifiedByName = "petsToPetIds")
    CustomerDTO toCustomerDTO(Customer c);

    @Named("petsToPetIds")
    default List<Long> petsToPetIds(List<Pet> source){
        if (source ==null) {
            return null;
        }
        List<Long> idList = new ArrayList<Long>();
        for (Pet p : source) {
            idList.add(p.getId());
        }

        return idList;
    };

    List<CustomerDTO> toCustomerDTOList(List<Customer> customers);
    Employee toEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO toEmployeeDTO(Employee employee);

}
