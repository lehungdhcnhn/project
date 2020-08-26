package com.example.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.admin.RoomController;
import com.example.converter.MovieConverter;
import com.example.converter.RoomConverter;
import com.example.dto.MovieDTO;
import com.example.dto.RoomDTO;
import com.example.entities.Movie;
import com.example.entities.Room;
import com.example.entities.schedule;
import com.example.repositories.MovieRepository;
import com.example.repositories.RoomRepository;
import com.example.repositories.ScheduleRepository;
import com.example.service.ICategoryService;
import com.example.service.IMovieService;
import com.example.service.impl.RoomService;
import com.example.service.impl.ScheduleService;

@Controller
public class MovieControllerWeb {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IMovieService movieService;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private MovieConverter movieConverter;
	@Autowired
	private RoomService roomService;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RoomConverter roomConverter;
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String viewHomePage(Model model) {
		List<Movie> movie = new ArrayList<Movie>();
		movie = movieRepository.getByMovieByDateEarly();
		List<MovieDTO> dto = new ArrayList<MovieDTO>();
		MovieDTO movieDto = new MovieDTO();
		for (Movie item : movie) {
			movieDto=movieConverter.convertToMovieDTO(item);
			movieDto.setThumbnailImagePathVer("/thumbnail-pictures/"+movieDto.getId()+"/"+movieDto.getThumbnail());
			dto.add(movieDto);
		}
		model.addAttribute("listMovie", dto);
		//diplay three item
		List<Movie> movieThree = new ArrayList<Movie>();
		movieThree = movieRepository.getByMovieByDate();
		List<MovieDTO> dtothree = new ArrayList<MovieDTO>();
		MovieDTO movieDtoThree = new MovieDTO();
		for (Movie item : movieThree) {
			movieDtoThree=movieConverter.convertToMovieDTO(item);
			movieDtoThree.setThumbnailImagePathVer("/thumbnail-pictures/"+movieDtoThree.getId()+"/"+movieDtoThree.getThumbnail());
			dtothree.add(movieDtoThree);
		}
		model.addAttribute("movieDtoThree", dtothree);
		return "homeWeb";
	}
	@RequestMapping(value="/home/listMovie",method = RequestMethod.GET)
	public String viewListMoviePage(Model model) {
		model.addAttribute("listCategory", categoryService.getAllCategory());
		List<Movie> movie = new ArrayList<Movie>();
		movie = movieRepository.getAllMovieDescDate();
		List<MovieDTO> dto = new ArrayList<MovieDTO>();
		MovieDTO movieDto = new MovieDTO();
		for (Movie item : movie) {
			movieDto=movieConverter.convertToMovieDTO(item);
			movieDto.setThumbnailImagePathVer("/thumbnail-pictures/"+movieDto.getId()+"/"+movieDto.getThumbnail());
			dto.add(movieDto);
			
		}
		
		model.addAttribute("listMovie", dto);
		
		return "MovieWeb/ListMovie";
	}

	@GetMapping("/home/listMovie/{id}")
	public String viewListMoviePageNow(Model model,@PathVariable(value = "id") long id) {
		List<Movie> movie = new ArrayList<Movie>();
		List<MovieDTO> dto = new ArrayList<MovieDTO>();
		MovieDTO movieDto = new MovieDTO();
		model.addAttribute("listCategory", categoryService.getAllCategory());
		if(id==2)
		{
		movie = movieRepository.findMovieByStart();
		for (Movie item : movie) {
			movieDto=movieConverter.convertToMovieDTO(item);
			movieDto.setThumbnailImagePathVer("/thumbnail-pictures/"+movieDto.getId()+"/"+movieDto.getThumbnail());
			dto.add(movieDto);
		}
		model.addAttribute("listMovie", dto);
		return "MovieWeb/listMovieCommingSoon";
		}else if(id==1)
		{
			movie = movieRepository.findMovieByEnd();
			for (Movie item : movie) {
				movieDto=movieConverter.convertToMovieDTO(item);
				movieDto.setThumbnailImagePathVer("/thumbnail-pictures/"+movieDto.getId()+"/"+movieDto.getThumbnail());
				dto.add(movieDto);
			}
			model.addAttribute("listMovie", dto);
			return "MovieWeb/listMovieCommingSoon";
		}
		return "/home/listMovie";
	}

	@RequestMapping("/descriptionMovie/{id}")
	public String viewDescriptionMovieGet(Model model,@PathVariable(value = "id") long id,
			@RequestParam(value = "startDate",required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date startDate, @RequestParam(value = "room_id", required = false)  Long roomId) {
		Movie movie = new Movie();
		List<schedule> schedule = new ArrayList<schedule>();
		List<RoomDTO> roomDTO = new ArrayList<RoomDTO>();
		RoomDTO dto = new RoomDTO();
		movie = movieService.findById(id);
		model.addAttribute("movie", movie);
		model.addAttribute("listRoom", roomService.getAllRoom());
		List<Room> room = new ArrayList<Room>(); 
		
		if(startDate==null)
		{
			startDate=new Date();
		}
		System.out.println(startDate);
		room = roomRepository.findRoomByMovieID(id,startDate);
		if(roomId==null||roomId==0)
		{
		
			for (Room item : room) {
				dto= roomConverter.convertToRoomDTO(item);
				schedule=scheduleRepository.findScheduleByDateAndRoom(startDate, dto.getId());
				dto.setSchedule(schedule);
				roomDTO.add(dto);
			}
		}
		else {
				schedule=scheduleRepository.findScheduleByDateAndRoom(startDate, roomId);
				dto.setSchedule(schedule);
				roomDTO.add(dto);	
		}
		model.addAttribute("listSchedule",roomDTO);
		return "MovieWeb/DescriptionMovie";	
	}
	@PostMapping("/searchMovieWeb")
	public String searchMovie(@RequestParam(value = "nameMovie") String searchMovie,Model model){
		List<Movie> movie = new ArrayList<Movie>();
		if(searchMovie=="")
		{
			return "MovieWeb/ListMovie";
		}
		movie = movieRepository.findMovieByName(searchMovie);
		if(movie.isEmpty())
		{
			return "MovieWeb/ListMovie";
		}
		
		List<MovieDTO> dto = new ArrayList<MovieDTO>();
		MovieDTO movieDto = new MovieDTO();
		for (Movie item : movie) {
			movieDto=movieConverter.convertToMovieDTO(item);
			dto.add(movieDto);
		}
		movieDto.setListResultMovie(dto);
		
		model.addAttribute("listMovie", movieDto.getListResultMovie());
		return "MovieWeb/ListMovie";
		
	}
	@GetMapping("/searchScheduleByRoom")
	public String viewDescriptionMovie(ModelMap model,@RequestParam(value = "movieId") long id,
			@RequestParam(value = "startDate",required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date startDate, @RequestParam(value = "room_id", required = false)  Long roomId) {
		Movie movie = new Movie();
		List<schedule> schedule = new ArrayList<schedule>();
		List<RoomDTO> roomDTO = new ArrayList<RoomDTO>();
		RoomDTO dto = new RoomDTO();
		movie = movieService.findById(id);
		model.addAttribute("movie", movie);
		model.addAttribute("listRoom", roomService.getAllRoom());
		List<Room> room = new ArrayList<Room>(); 
		
		if(startDate==null)
		{
			startDate=new Date();
		}
		System.out.println(startDate);
		room = roomRepository.findRoomByMovieID(id,startDate);
		if(roomId==null||roomId==0)
		{
		
			for (Room item : room) {
				dto= roomConverter.convertToRoomDTO(item);
				schedule=scheduleRepository.findScheduleByDateAndRoom(startDate, dto.getId());
				dto.setSchedule(schedule);
				roomDTO.add(dto);
			}
		}
		else {
				schedule=scheduleRepository.findScheduleByDateAndRoom(startDate, roomId);
				dto.setSchedule(schedule);
				roomDTO.add(dto);	
		}
		model.addAttribute("listSchedule",roomDTO);
		return "MovieWeb/Schedule :: listScheduleSearch";	
	}
}

