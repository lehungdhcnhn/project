package com.example.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.Room;
import com.example.service.IRoomService;

@Controller
public class RoomController {
	@Autowired
	private IRoomService roomService;
	
	
	@GetMapping("/admin/listRoom")
	public String getViewRoomList(Model model) {
		model.addAttribute("listRoom", roomService.getAllRoom());
		return "room/listRoom";
	}
	
	@GetMapping("/admin/editRoom")
	public String viewEditRoom(Model model) {
		Room room = new Room();
		model.addAttribute("room", room);
		return "room/editRoom";
	}
	
	@GetMapping("/updateRoom/{roomId}")
	public String viewUpdateRoom(@PathVariable(value = "roomId") long roomId, Model model) {
		Room room = roomService.getRoombyId(roomId);
		model.addAttribute("room", room);
		return "room/updateRoom";
	}
	
	@GetMapping("/deleteRoom/{roomId}")
	public String deleteRoom(@PathVariable(value = "roomId") long roomId) {
		roomService.deleteRoombyId(roomId);
		return "redirect:/admin/listRoom";
	}
	
	@PostMapping("/saveRoom")
	public String updateRoom(@ModelAttribute("room") Room room) {
		roomService.saveRoom(room);
		return "redirect:/admin/listRoom";
	}
}
