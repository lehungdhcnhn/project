package com.example.service;

import java.util.List;

import com.example.dto.scheduleDTO;

public interface IScheduleService {
	List<scheduleDTO> findAll();

	scheduleDTO findById(long id);
	scheduleDTO save(scheduleDTO dto);
	void delete(long[] ids);
}
