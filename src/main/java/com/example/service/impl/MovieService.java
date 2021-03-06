package com.example.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.converter.MovieConverter;
import com.example.dto.MovieDTO;
import com.example.entities.Movie;
import com.example.entities.Room;
import com.example.repositories.MovieRepository;
import com.example.service.IMovieService;

@Service

public class MovieService implements IMovieService {
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private MovieConverter movieConverter;

	/*
	 * @Override public List<MovieDTO> getAll(Pageable pageable) { List<MovieDTO>
	 * models = new ArrayList<>(); List<Movie> entities = (List<Movie>)
	 * movieRepository.findAll(pageable).getContent(); for (Movie item : entities) {
	 * MovieDTO newDTO = movieConverter.convertToMovieDTO(item); models.add(newDTO);
	 * } return models; }
	 */
	@Override
	public List<MovieDTO> getAll() {
		List<MovieDTO> models = new ArrayList<>();
		List<Movie> entities = (List<Movie>) movieRepository.findAll();
		for (Movie item : entities) {
			MovieDTO newDTO = movieConverter.convertToMovieDTO(item);
			models.add(newDTO);
		}
		return models;
	}

	@Override
	public Movie findById(Long movieId) {
		Optional<Movie> entity = movieRepository.findById(movieId);
		if (!entity.isPresent()) {
			throw new RuntimeException("Movie Not Found!");
		}
		return entity.get();
	}

	@Override
	public Movie create(Movie movieDetails) {
		movieRepository.save(movieDetails);
		return movieDetails;
	}

	@Override
	public void delete(Long movieId) {

		movieRepository.deleteById(movieId);

	}

	

	/*
	 * @Override public List<Movie> getAll() { List<Movie> entities = (List<Movie>)
	 * movieRepository.findAll(); return entities; }
	 */

	@Override
	public void deleleFile(Long id) {
		Movie curentMovie = findById(id);
		String uploadDir = "C:/Users/Dell/Desktop/thumbnail-pictures/" + id;
		String upload = "C:/Users/Dell/Desktop/thumbnail-pictures/" + id + "/"
				+ curentMovie.getThumbnail();

		Path uploadPath = Paths.get(uploadDir);
		Path uploadS = Paths.get(upload);
		if (Files.exists(uploadPath)) {
			try {
				Files.delete(uploadS);
				Files.delete(uploadPath);
			} catch (NoSuchFileException x) {
				System.err.format("%s: no such" + " file or directory%n", uploadPath);
			} catch (DirectoryNotEmptyException x) {
				System.err.format("%s not empty%n", uploadPath);
			} catch (IOException x) {
				System.err.println(x);
			}
		}

	}

	@Override
	public void saveFile(Movie movie, String fileName, MultipartFile multipartFile) throws IOException {
		movie.setThumbnail(fileName);
		Movie saveMovie = save(movie);
		String uploadDir = "C:/Users/Administrator/Desktop/project/thumbnail-pictures/" + saveMovie.getId();
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try {
			InputStream inputStream = multipartFile.getInputStream();
			Path filePath = uploadPath.resolve(fileName);
			System.out.println(filePath.toFile().getAbsolutePath());
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			throw new IOException("khong the upload file: " + fileName);
		}

	}

	

	@Override
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
		
	}

	@Override
	public long getNumOfMovie() {
		return movieRepository.count();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Slice<Movie> findAll(int page, int size) {
		return movieRepository.findAll(new PageRequest(page, size));
	}

	@Override
	public boolean hasRoomById(long Id) {
		return movieRepository.existsById(Id);
	}

}
