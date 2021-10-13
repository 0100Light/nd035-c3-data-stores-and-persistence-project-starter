package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule saveSchedule(Schedule s) {
        Schedule saved = scheduleRepository.save(s);
        return saved;
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
//        schedules.get(0).getEmployees()
        // TODO: change to eager loading and maybe only load when converting to DTO

        return schedules;
    }
}
