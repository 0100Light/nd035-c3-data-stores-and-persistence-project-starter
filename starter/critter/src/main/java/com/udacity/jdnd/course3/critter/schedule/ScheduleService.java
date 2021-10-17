package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Embeddable;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class ScheduleService {
    @PersistenceContext
    EntityManager em;

    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public Schedule saveSchedule(Schedule s) {
        Schedule saved = scheduleRepository.save(s);
        return saved;
    }

    @Transactional
    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        for (Schedule s: schedules){
            List<Employee> yees = s.getEmployees();
        }
        // TODO: change to eager loading and maybe only load when converting to DTO

        return schedules;
    }

    @Transactional
    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return scheduleRepository.findAllByEmployeesId(employeeId);
    }

    // 遇到no session error 的時候常常可以用這個解決
    // 這個annotation表示整個method是一個transaction，transaction結束後session才會關。
    @Transactional
    public List<Schedule> getScheduleForPet(Long petId) {
/*
        List<Schedule> schedules = scheduleRepository.findAll();
        List<Schedule> filtered = new ArrayList<>();
        for (Schedule s: schedules){
            Boolean selected = false;
            for (Pet p: s.getPets()){
                if (p.getId() == petId){ selected = true; }
            }
            filtered.add(s);
        }
        return filtered;
*/

/*
        EntityGraph<Schedule> eg = em.createEntityGraph(Schedule.class);
        eg.addAttributeNodes("id");
        eg.addAttributeNodes("pets");
        eg.addAttributeNodes("date");
        eg.addAttributeNodes("activities");
        eg.addAttributeNodes("employees");
//        eg.addSubgraph("pets").addAttributeNodes("id");
//        Map<String, Object> props = new HashMap<>();
//        props.put("javax.persistence.fetchgraph", eg);

        List<Schedule> ss = em.createQuery("select s from Schedule s")
                .setHint("javax.persistence.fetchgraph", eg)
                .getResultList();
        return ss;
*/
        return scheduleRepository.findAllByPetsId(petId);
    }

    @Transactional
    public List<Schedule> getScheduleForCustomer(Long customerId) {
        return scheduleRepository.findByPets_Customer_Id(customerId);
    }
}
