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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	/////////
	@RequestMapping(value = "/multipleScheduleChange", method = RequestMethod.POST)
	public String deleteScheduleList(@RequestParam(value="scheduleId",required = false) Long[] scheduleListId) {
		if(scheduleListId==null)
		{
			return "redirect:/admin/listSchedule?error";
		}
		else {
			for(long scheduleId : scheduleListId) {
				scheduleService.delete(scheduleId);	
			}
			return "redirect:/admin/listSchedule";
		}
	}
	
	@RequestMapping(value = "/multipleScheduleChange" , method = RequestMethod.POST , params = "action=updateListSchedule")
	public String viewScheduleList(@RequestParam(value="scheduleId",required = false) long[] listScheduleId ,Model model) {
		if(listScheduleId==null){
			return "redirect:/admin/listSchedule?errorUpdate";
		}
		else {
			scheduleDTO scheduleDTO = new scheduleDTO();
			List<scheduleDTO> schedules = new ArrayList<>();
			scheduleDTO dto = new scheduleDTO();
			for(long scheduleId : listScheduleId) {
				dto = scheduleService.findById(scheduleId);
				schedules.add(dto);
			}
			scheduleDTO.setListResult(schedules);
			model.addAttribute("listMovie", movieService.getAll());
			model.addAttribute("listRoom",roomService.getAllRoom());
			model.addAttribute("listSchedule", scheduleDTO);
			return "schedule/updateListSchedule";
		}
	}
	
	@RequestMapping(value = "/saveListSchedule", method =RequestMethod.POST)
	public String saveListRoom(@ModelAttribute("listSchedule") scheduleDTO scheduleDTO) {
		for(scheduleDTO schedule : scheduleDTO.getListResult()) {
			scheduleService.save(schedule);
		}
		return "redirect:admin/listSchedule";
		
	}
}
