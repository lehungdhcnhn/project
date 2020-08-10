package com.example.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.dto.MovieDTO;
import com.example.dto.scheduleDTO;
import com.example.service.IMovieService;
import com.example.service.IRoomService;
import com.example.service.IScheduleService;

@Controller
public class scheduleController {
	@Autowired
	private IScheduleService scheduleService;
	
	@Autowired
	private IRoomService roomService;
	@Autowired
	private IMovieService movieService;
	
	@GetMapping("/admin/listSchedule")
	public String getViewRoomList(Model model) {
		List<scheduleDTO>dto = new ArrayList<scheduleDTO>();
		dto=scheduleService.findAll();
		model.addAttribute("listSchedule", dto);
		return "schedule/listSchedule";
	}
	@GetMapping("/admin/editSchedule")
	public String viewEditPage(Model model) {
		model.addAttribute("listRoom",roomService.getAllRoom());
		model.addAttribute("listMovie",movieService.getAll());
		scheduleDTO schedule = new scheduleDTO();
		model.addAttribute("schedule", schedule);
		return "schedule/editSchedule";
	}


}
