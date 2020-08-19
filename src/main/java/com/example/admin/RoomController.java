package com.example.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.modelmapper.internal.bytebuddy.asm.Advice.Exit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dto.RoomDTO;
import com.example.entities.Room;
import com.example.service.IRoomService;

@Controller
public class RoomController {
	@Autowired
	private IRoomService roomService;
	private int pageSize = 6;

	@GetMapping("/admin/listRoom/{page}")
	public String getViewPageRoomList(Model model, @PathVariable(value = "page") int pageNumber) {
		long totalRoom = roomService.numOfRoom();
		long numOfPage = (totalRoom - totalRoom % pageSize) / pageSize + 1;
		if (totalRoom % pageSize == 0)
			numOfPage--;
		System.err.println(pageNumber);
		model.addAttribute("listRoom", roomService.findAll(pageNumber - 1, pageSize));
		model.addAttribute("numOfPage", numOfPage);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("numOfEntity", totalRoom);
		model.addAttribute("pageSize", pageSize);
		return "room/listRoom";
	}

	@GetMapping("/admin/listRoom/lastPage")
	public String getViewLastPageRoomList(Model model) {
		long totalRoom = roomService.numOfRoom();
		long numOfPage = (totalRoom - totalRoom % pageSize) / pageSize + 1;
		return "redirect:/admin/listRoom/" + numOfPage;
	}

//	@GetMapping("/api/admin/listRoom/{page}")
//	public @ResponseBody Slice<Room> getListRoomByPageNumber(@PathVariable(value = "page") int pageNumber) {
//		return roomService.findAll(pageNumber, pageSize);
//	}

	@GetMapping("/admin/listRoom")
	public String getViewRoomList(Model model) {
		return "redirect:/admin/listRoom/1";
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

	@RequestMapping(value = "/multipleRoomChange", method = RequestMethod.POST)
	public String deleteRoomList(@RequestParam("roomId") Long[] roomListId) {
		for (long roomId : roomListId) {
			roomService.deleteRoombyId(roomId);
		}
		return "redirect:/admin/listRoom";
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
	public String saveRoom(@ModelAttribute("room") Room room, @Valid Room roomValid, BindingResult bindingResult) {
		System.err.println(bindingResult.getFieldErrorCount());
		if (bindingResult.hasErrors()) {
			return "room/updateRoom";
		}
		roomService.saveRoom(room);
//		if(roomService.hasRoomById( room.getId() )){
//			
//			
//			
//			return "redirect:/admin/listRoom/"+ pageNumber;
//		};
//		
		return "redirect:/admin/listRoom/lastPage";
	}
}
