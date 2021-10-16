package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private PetRepository petRepository;

    @Autowired
    UserMapper userMapper;

    public UserService(CustomerRepository customerRepository,
                       EmployeeRepository employeeRepository,
                       PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    public CustomerDTO saveCustomer(CustomerDTO dto){
        Customer c = userMapper.toCustomer(dto);
        Customer saved = customerRepository.save(c);
        return AppMapper.INSTANCE.toCustomerDTO(saved);
    }

    @Transactional
    public List<CustomerDTO> getCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return AppMapper.INSTANCE.toCustomerDTOList(customers);
    }


    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee y = AppMapper.INSTANCE.toEmployee(employeeDTO);
        Employee saved = employeeRepository.save(y);
        return AppMapper.INSTANCE.toEmployeeDTO(saved);
    }

    @Transactional
    public EmployeeDTO getEmployeeById(long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()){
            Employee y = optionalEmployee.get();
            return AppMapper.INSTANCE.toEmployeeDTO(y);
        } else {
            return new EmployeeDTO();
        }
    }

    @Transactional
    public CustomerDTO getCustomerByPet(long petId) {
        Optional<Pet> p = petRepository.findById(petId);
        if (p.isPresent()){
            Customer c = p.get().getCustomer();
            return AppMapper.INSTANCE.toCustomerDTO(c);
        }
        return new CustomerDTO();
    }

    @Transactional
    public void setEmployeeAvailability(long employeeId, Set<DayOfWeek> daysAvailable) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()){
            Employee y = optionalEmployee.get();
            y.setDaysAvailable(daysAvailable);
            employeeRepository.save(y);
        } else {
            throw new ObjectNotFoundException(employeeId, "Employee");
        }
    }

    @Transactional
    public Optional<Customer> getCustomerById(Long id){
        return customerRepository.findById(id);
    }

    // TODO: implement
//    public List<EmployeeDTO> findEmployeeByRequest(EmployeeRequestDTO requestDTO){
//        List<Employee> ees = employeeRepository.findAllBySkillsContainingAndDaysAvailableContaining(requestDTO.getSkills(), requestDTO.getDate().getDayOfWeek());
//        return userMapper.toEmployeeDtoList(ees);
//    }

    @Transactional
    public List<Employee> getAvailableEmployees(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        List<Employee> employees = employeeRepository.findAllByDaysAvailableContaining(dayOfWeek);
        List<Employee> availableEmployees = new ArrayList<>();
        for(Employee e : employees){
            if(e.getSkills().containsAll(skills)) {
                availableEmployees.add(e);
            }
        }
        return availableEmployees;
    }

}
