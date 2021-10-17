package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // TODO: try EntityGraph

    List<Schedule> findAllByEmployeesId(long employees_id);
    List<Schedule> findAllByPetsId(long petId);

    List<Schedule> findByPets_Customer_Id(long id);

    @Query("select s from Schedule s left join s.pets pets where pets.customer.name = ?1")
    List<Schedule> findByPets_Customer_Name(String name);


}