package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    PetRepository petRepository;

    public UserMapper() {
    }


    @Mapping(source = "petIds", target = "pet")
    abstract Customer toCustomer(CustomerDTO dto);

    @Mapping(source = "pet", target = "petIds", qualifiedByName = "petsToPetIds")
    abstract CustomerDTO toCustomerDTO(Customer c);

    abstract List<EmployeeDTO> toEmployeeDtoList(List<Employee> employeeList);
    abstract List<Employee> toEmployees(List<EmployeeDTO> employeeDTOList);


    protected List<Pet> petIdsToPets(List<Long> ids){
        if (ids == null) return null;
        List<Pet> pets = new ArrayList<>();
        pets = petRepository.findAllById(ids);
        return pets;
    }

    @Named("petsToPetIds")
    protected List<Long> petsToPetIds(List<Pet> source){
        List<Long> idList = new ArrayList<>();
        for (Pet p : source) {
            idList.add(p.getId());
        }

        return idList;
    }
}
