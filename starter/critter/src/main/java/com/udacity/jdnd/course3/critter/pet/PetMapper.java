package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class PetMapper {
    @Autowired
    private UserService userService;

    public PetMapper() {
    }

    public PetDTO toPetDto(Pet p){
        PetDTO dto = new PetDTO();
        dto.setId(p.getId());
        dto.setType(p.getPetType());
        dto.setName(p.getName());
        dto.setOwnerId(p.getCustomer().getId());
//        if (p.getCustomer() != null){ dto.setOwnerId(p.getCustomer().getId()); }
        dto.setBirthDate(p.getBirthDate());
        dto.setNotes(p.getNotes());

        return dto;
    }


    @Named("petsToPetIds")
    protected List<Long> petsToPetIds(List<Pet> source){
        List<Long> idList = null;
        for (Pet p : source) {
            idList.add(p.getId());
        }

        return idList;
    };

//    @Mapping(target = "customer", ignore = true)
//    @Mapping(source = "type", target = "petType")
//    @Mapping(source = "ownerId", target ="customer", qualifiedByName = "")
    public Pet toPet(PetDTO petDTO){
        Pet p = new Pet();
        if (petDTO.getId() > 0){ p.setId(petDTO.getId()); }
        if (petDTO.getOwnerId() > 0){
            Optional<Customer> c = userService.getCustomerById(petDTO.getOwnerId());
            if (c.isPresent()){
                p.setCustomer(c.get());
            }
        }
        p.setPetType(petDTO.getType());
        p.setName(petDTO.getName());
        p.setBirthDate(petDTO.getBirthDate());
        p.setNotes(petDTO.getNotes());

        return p;
    };


    public abstract List<PetDTO> toPetDTOList(List<Pet> pets);
    public abstract List<Pet> toPets(List<PetDTO> petDTOList);

/*
    PetDTO toPetDTO(Pet p);

    List<PetDTO> toPetDTOList(List<Pet> pets);
    List<Pet> toPets(List<PetDTO> petDTOList);

    @Named("customerIdToCustomer")
    public static Customer customerIdToCustomer(Long customerId){

    }
*/
}
