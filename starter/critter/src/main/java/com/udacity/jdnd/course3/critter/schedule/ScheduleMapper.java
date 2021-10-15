package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class ScheduleMapper {
    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public ScheduleMapper() {
    }

    @Mapping(source = "employeeIds", target = "employees")
    @Mapping(source = "petIds", target = "pets")
    abstract Schedule toSchedule(ScheduleDTO dto);

    @Mapping(source = "pets", target = "petIds")
    @Mapping(source = "employees", target = "employeeIds")
    abstract ScheduleDTO scheduleDTO(Schedule schedule);

    abstract List<Schedule> toSchedules(List<ScheduleDTO> scheduleDTOS);
    abstract List<ScheduleDTO> toScheduleDTOS(List<Schedule> schedules);

    abstract List<Long> petsToPetIds(List<Pet> pets);
    abstract List<Pet> petIdsToPets(List<Long> petIds);

    abstract List<Employee> EmployeeIdsToEmployees(List<Long> employeeIds);

    protected Employee EmployeeIdToEmployee(Long employeeId){
        Optional<Employee> oe = employeeRepository.findById(employeeId);
        return oe.orElse(new Employee());
    }


    abstract List<Long> EmployeesToEmployeeIds(List<Employee> employees);

    protected Long EmployeeToEmployeeId(Employee employee){
        return employee.getId();
    }

    protected Long PetToPetId(Pet p){
        return p.getId();
    }

    protected Pet PetIdToPet(Long petid){
        Optional<Pet> p =petRepository.findById(petid);
        return p.orElse(new Pet());
    }
}
