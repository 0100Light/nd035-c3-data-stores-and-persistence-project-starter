package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private PetRepository petRepository;
    private CustomerRepository customerRepository;
    private PetMapper petMapper;

    public PetService(PetRepository petRepository,
                      CustomerRepository customerRepository,
                      PetMapper petMapper) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
        this.petMapper = petMapper;
    }

    @Transactional
    public Pet savePet(Pet p) {
        Customer c = customerRepository.save(p.getCustomer());
        Pet saved = petRepository.save(p);
        c.getPet().add(saved);
        return saved;
    }

    @Transactional
    public PetDTO getPetById(long petId) {
        Optional<Pet> p = petRepository.findById(petId);
        Pet res = p.orElse(new Pet());
        return petMapper.toPetDto(res);
    }

    @Transactional
    public List<PetDTO> getAllPets() {
        List<Pet> pets = petRepository.findAll();
        return petMapper.toPetDTOList(pets);
    }

    @Transactional
    public List<Pet> getPetsByOwner(long ownerId) {
        Optional<Customer> oc = customerRepository.findById(ownerId);
        Customer c=  oc.orElse(null);

        List<Pet> pets = c.getPet();
        return pets;
    }
}
