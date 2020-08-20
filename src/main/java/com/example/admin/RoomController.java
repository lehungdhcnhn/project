package com.example.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		return "redirect:/admin/Room?page=1&limit=4";
	}
	
	
	@GetMapping("/admin/Room")
	public String getViewPageRoom(Model model, @RequestParam(value = "page") int pageNumber, @RequestParam(value = "limit") int pageSize) {
		long numOfRoom = roomService.getNumOfRoom();
		long numOfPage =  (long)(numOfRoom/pageSize+1);
		if(numOfRoom%pageSize==0) numOfPage--;
		System.err.println(pageNumber+"|"+numOfPage);
		Slice<RoomDTO> sliceRoomDTO= roomService.findAll(pageNumber-1, pageSize);
		model.addAttribute("listRoom", sliceRoomDTO);
		model.addAttribute("numOfPage", numOfPage);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("numOfEntity", numOfRoom);
		model.addAttribute("pageSize", pageSize);
		return "Room/listRoom";
	}

	@GetMapping("/admin/editRoom")
	public String viewEditRoom(Model model) {
		RoomDTO room = new RoomDTO();
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

	@RequestMapping(value = "/multipleRoomChange", method = RequestMethod.POST)
	public String deleteRoomList(@RequestParam(value = "roomId",required = false) long[] roomListId) {
		if (roomListId != null) {
			for (long roomId : roomListId) {
				roomService.deleteRoombyId(roomId);
			}
			return "redirect:/admin/listRoom?success";
		} else
			return "redirect:/admin/listRoom?error";

	}

	@RequestMapping(value = "/multipleRoomChange", method = RequestMethod.POST, params = "action=updateRoomList")
	public String viewUpdateRoomList(@RequestParam("roomId") long[] listRoomId, Model model) {
		RoomDTO roomDTO = new RoomDTO();
		List<Room> rooms = new ArrayList<>();
		for (long roomId : listRoomId) {
			rooms.add(roomService.getRoombyId(roomId));
		}
		roomDTO.setListRoom(rooms);
		model.addAttribute("listRoom", roomDTO);
		return "room/updateListRoom";
	}

	@RequestMapping(value = "/saveListRoom", method = RequestMethod.POST)
	public String saveListRoom(@Valid @ModelAttribute("listRoom") RoomDTO roomDTO, BindingResult bindingResult) {
		System.err.println(bindingResult.getFieldErrorCount());
		if (bindingResult.hasErrors()) {
			return "room/updateListRoom";
		} else {
			for (Room room : roomDTO.getListRoom()) {
				roomService.saveRoom(room);
			}
			return "redirect:admin/listRoom";
		}

	}

	@PostMapping("/saveRoom")
	public String updateRoom(@ModelAttribute("room") Room room, @Valid Room roomValid, BindingResult bindingResult) {
		System.err.println(bindingResult.getFieldErrorCount());
		if (bindingResult.hasErrors()) {
			return "room/updateRoom";
		}
		roomService.saveRoom(room);
		return "redirect:/admin/listRoom";
	}
}
