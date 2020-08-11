package com.example.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dto.MovieDTO;
import com.example.dto.scheduleDTO;
import com.example.entities.Movie;
import com.example.entities.Room;
import com.example.entities.schedule;
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
	@PostMapping("/saveSchedule")
	public String updateRoom(@ModelAttribute("schedule") scheduleDTO scheduleDTO) {
		scheduleService.save(scheduleDTO);
		return "redirect:/admin/listSchedule";
	}
	@GetMapping("/updateSchedule/{id}")
	public String updateSchedule(@PathVariable(value = "id") long id, Model model) {
		model.addAttribute("listMovie", movieService.getAll());
		model.addAttribute("listRoom",roomService.getAllRoom());
		scheduleDTO dto = scheduleService.findById(id);
		model.addAttribute("schedule", dto);
		return "schedule/updateSchedule";
	}
	@GetMapping("/deleteSchedule/{id}")
	public String deleteMovie(@PathVariable(value = "id") long id){
		scheduleService.delete(id);
		return "redirect:/admin/listSchedule";
	}

}
