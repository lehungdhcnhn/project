package com.example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.dto.scheduleDTO;
import com.example.entities.schedule;
@Component
public class ScheduleConverter {
	static ModelMapper modelMapper = new ModelMapper();
	public scheduleDTO convertToCatgoryDTO(schedule schedule)
	{
		scheduleDTO scheduleDTO = modelMapper.map(schedule, scheduleDTO.class);
		return scheduleDTO;
	}
	public schedule convertToCategoryEntity(scheduleDTO scheduleDTO)
	{
		schedule schedule = modelMapper.map(scheduleDTO, schedule.class);
		return schedule;
	}
}
