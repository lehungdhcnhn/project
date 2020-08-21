package com.example.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.dto.scheduleDTO;

public interface IScheduleService {
	List<scheduleDTO> findAll(Pageable pageable);

	scheduleDTO findById(long id);
	scheduleDTO save(scheduleDTO dto);
	void delete(long id);
	Integer getToTalItem();
}
