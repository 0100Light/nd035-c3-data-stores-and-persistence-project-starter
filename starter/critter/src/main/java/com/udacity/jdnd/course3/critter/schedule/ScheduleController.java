package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;
    private ScheduleMapper scheduleMapper;

    public ScheduleController(ScheduleService scheduleService,
                              ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule s = scheduleMapper.toSchedule(scheduleDTO);
        Schedule saved = scheduleService.saveSchedule(s);
        return scheduleMapper.scheduleDTO(saved);
    }

    @Transactional
    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return scheduleMapper.toScheduleDTOS(schedules);
    }

    @Transactional
    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> s = scheduleService.getScheduleForPet(petId);
        return scheduleMapper.toScheduleDTOS(s);
    }

    @Transactional
    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
        return scheduleMapper.toScheduleDTOS(schedules);
    }

    @Transactional
    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
        return scheduleMapper.toScheduleDTOS(schedules);
    }
}
