package com.example.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.converter.MovieConverter;
import com.example.dto.MovieDTO;
import com.example.entities.Movie;
import com.example.repositories.MovieRepository;
import com.example.repositories.ScheduleRepository;
import com.example.service.ICategoryService;
import com.example.service.IMovieService;

@Controller
public class MovieController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IMovieService movieService;
	@Autowired
	private ScheduleRepository scheduleRepositoty;
	@Autowired
	private MovieConverter movieConverter;
	@Autowired
	private MovieRepository movieRepository;
	@GetMapping("/admin/listMovie")
	public String getViewMovieList(Model model) {
		return "redirect:/admin/Movie?page=1&limit=4";
	}
	@GetMapping("/admin/Movie")
	public String getViewPageRoom(Model model, @RequestParam(value = "page") int pageNumber, @RequestParam(value = "limit") int pageSize) {
		long numOfRoom = movieService.getNumOfMovie();
		long numOfPage =  (long)(numOfRoom/pageSize+1);
		if(numOfRoom%pageSize==0) numOfPage--;
		System.err.println(pageNumber+"|"+numOfPage);
		
		Slice<Movie> movie = movieService.findAll(pageNumber-1, pageSize);
		model.addAttribute("listMovie", movie);
		model.addAttribute("numOfPage", numOfPage);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("numOfEntity", numOfRoom);
		model.addAttribute("pageSize", pageSize);
		
	
		return "Movie/listMovie";
	}

	/*
	 * @RequestMapping(value="/admin/listMovie",method = RequestMethod.GET) public
	 * String viewHomePage(Model model) { MovieDTO dto = new MovieDTO();
	 * dto.setListResultMovie(movieService.getAll());
	 * model.addAttribute("listMovie", dto); return "Movie/listMovie"; }
	 */

	@GetMapping("/admin/editMovie")
	public String viewEditPage(Model model) {
		model.addAttribute("listCategory", categoryService.getAllCategory());
		MovieDTO movie = new MovieDTO();
		model.addAttribute("movie", movie);
		return "Movie/editMovie";
	}

	
	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") Movie movie,@RequestParam("fileImage") MultipartFile multipartFile, @Valid Movie movieValid,BindingResult bindingResult) throws IOException
	{
		if(bindingResult.hasErrors())
		{
			return "movie/editMovie";
		}
		String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if(!fileName.equals("")){
			movieService.saveFile(movie, fileName, multipartFile);
		}else{
			movieService.save(movie);
		}
		return "redirect:/admin/listMovie";
	}

	@GetMapping("/updateMovie/{id}")
	public String updateMive(@PathVariable(value = "id") long id, Model model) {
		model.addAttribute("listCategory", categoryService.getAllCategory());
		Movie movie = movieService.findById(id);
		model.addAttribute("movie", movie);
		return "Movie/updateMovie";
	}

	@GetMapping("/deleteMovie/{id}")
	public String deleteMovie(@PathVariable(value = "id") long id){
		scheduleRepositoty.deleteFindByMovieID(id);
		movieService.deleleFile(id);
		movieService.delete(id);
		return "redirect:/admin/listMovie";
	}
	@PostMapping("/updateMovieLate/{id}")
	public String updateMovie( @ModelAttribute("movie") Movie movie,@RequestParam("fileImage") MultipartFile multipartFile,@PathVariable(value = "id") long id,@Valid Movie movieValid, BindingResult bindingResult) throws IOException
	{
		if(bindingResult.hasErrors())
		{
			return "movie/updateMovie";
		}
		String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if(!fileName.equals("")){
			movieService.deleleFile(id); 
			movieService.saveFile(movie, fileName, multipartFile);
		}
		else{
			movieService.save(movie);
		}
		return "redirect:/admin/listMovie";
	}
	///////////////////////
	@RequestMapping(value = "/multipleMovieChange", method = RequestMethod.POST )
	public String deleteRoomList(@RequestParam(value="movieId",required = false) long[] movieListId) {
		if(movieListId==null)
		{
			return "redirect:/admin/listMovie?error";
		}
		else { 
			for(long movieId : movieListId) {
				scheduleRepositoty.deleteFindByMovieID(movieId);
				movieService.deleleFile(movieId);
				movieService.delete(movieId);
			}
			return "redirect:/admin/listMovie?success";
		}
	}
	//////////////////
	@GetMapping("/movie/export")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		String fileName="movie.csv";
		
		String headerKey ="Content-Disposition";
		String headerValue="attachment; filename="+fileName;
		
		response.setHeader(headerKey, headerValue);
		List<MovieDTO> movieDto =movieService.getAll(); 
		List<Movie> movie = new ArrayList<Movie>();
		Movie entity = new Movie();
		for (MovieDTO item : movieDto) {
			entity = movieConverter.convertToMovieEntity(item);
			movie.add(entity);
		}
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String [] csvHeader= {"name","starttime","content","length","member"};
		String [] nameMapping= {"name","starttime","content","length","member"};
		csvWriter.writeHeader(csvHeader);
		for (Movie item : movie ) {
			csvWriter.write(item, nameMapping);
		}
		csvWriter.close();
	}	
	@GetMapping("/searchMovie/{searchMovie}")
	public String deleteMovie(@PathVariable(value = "searchMovie") String searchMovie,Model model){
		List<Movie> movie = new ArrayList<Movie>();
		movie = movieRepository.findMovieByName(searchMovie);
		List<MovieDTO> dto = new ArrayList<MovieDTO>();
		MovieDTO movieDto = new MovieDTO();
		for (Movie item : movie) {
			movieDto=movieConverter.convertToMovieDTO(item);
			dto.add(movieDto);
		}
		movieDto.setListResultMovie(dto);
		model.addAttribute("listMovie", movieDto);
		return "Movie/listMovie";
	}
}
