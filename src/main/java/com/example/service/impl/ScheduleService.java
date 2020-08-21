package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.converter.ScheduleConverter;
import com.example.dto.scheduleDTO;
import com.example.entities.schedule;
import com.example.repositories.ScheduleRepository;
import com.example.service.IScheduleService;

@Service
public class ScheduleService implements IScheduleService{
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private ScheduleConverter scheduleConverter;
	
	@Override
	public List<scheduleDTO> findAll(Pageable pageable) {
		List<scheduleDTO> models = new ArrayList<scheduleDTO>();
		List<schedule> entities = scheduleRepository.findAll(pageable).getContent();
		for (schedule item: entities) {
			scheduleDTO scheduleDTO = scheduleConverter.convertToCatgoryDTO(item);
			models.add(scheduleDTO);
		}
		return models;
	}

	@Override
	public scheduleDTO findById(long id) {
		Optional<schedule> entity = scheduleRepository.findById(id);
		if (!entity.isPresent()) {
			throw new RuntimeException("Movie Not Found!");
		}
		return scheduleConverter.convertToCatgoryDTO(entity.get());
	}

	@Override
	public scheduleDTO save(scheduleDTO dto) {
		schedule scheduleEntity =scheduleConverter.convertToCategoryEntity(dto);
		return scheduleConverter.convertToCatgoryDTO(scheduleRepository.save(scheduleEntity));
	}

	@Override
	
	public void delete(long id) {
		
			scheduleRepository.deleteById(id);
		
	}

	@Override
	public Integer getToTalItem() {
		// TODO Auto-generated method stub
		return (int)scheduleRepository.count();
	}

}
