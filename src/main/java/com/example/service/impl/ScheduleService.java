package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private RoomService roomService;
	@Autowired
	private MovieService movieService;
	@Override
	public List<scheduleDTO> findAll() {
		List<scheduleDTO> models = new ArrayList<scheduleDTO>();
		List<schedule> entities = scheduleRepository.findAll();
		for (schedule item: entities) {
			scheduleDTO scheduleDTO = scheduleConverter.convertToCatgoryDTO(item);
			/*
			 * scheduleDTO.setRoomName(roomService.getRoombyId(scheduleDTO.getRoomId()));
			 * scheduleDTO.setMovieName(movieService.findById(scheduleDTO.getMovieId()));
			 */
			models.add(scheduleDTO);
		}
		return models;
	}

	@Override
	public scheduleDTO findById(long id) {
		return null;
	}

	@Override
	public scheduleDTO save(scheduleDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long[] ids) {
		// TODO Auto-generated method stub
		
	}

}
