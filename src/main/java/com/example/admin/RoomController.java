package com.example.admin;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

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

import com.example.dto.RoomDTO;
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
	
	@RequestMapping(value = "/multipleRoomChange", method = RequestMethod.POST,params = "action=deleteRoomList" )
	public String deleteRoomList(@RequestParam("roomId") Long[] roomListId) {
		for(long roomId : roomListId) {
			roomService.deleteRoombyId(roomId);	
		}
		return "redirect:/admin/listRoom";
	}
	
	@RequestMapping(value = "/multipleRoomChange" , method = RequestMethod.POST , params = "action=updateRoomList")
	public String viewUpdateRoomList(@RequestParam("roomId") long[] listRoomId ,Model model) {
		RoomDTO roomDTO = new RoomDTO();
		List<Room> rooms = new ArrayList<>();
		for(long roomId : listRoomId) {
			rooms.add(roomService.getRoombyId(roomId));
		}
		roomDTO.setListRoom(rooms);
		model.addAttribute("listRoom", roomDTO);
		return "room/updateListRoom";
	}
	
	@RequestMapping(value = "/saveListRoom", method =RequestMethod.POST)
	public String saveListRoom(@ModelAttribute("listRoom") RoomDTO roomDTO) {
		for(Room room : roomDTO.getListRoom()) {
			roomService.saveRoom(room);
		}
		return "redirect:admin/listRoom";
		
	}
	
	@PostMapping("/saveRoom")
	public String updateRoom(@ModelAttribute("room") Room room) {
		roomService.saveRoom(room);
		return "redirect:/admin/listRoom";
	}
}
