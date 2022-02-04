package com.example.entrevueSpringBoot.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.entrevueSpringBoot.exception.FilmNotFoundException;
import com.example.entrevueSpringBoot.model.Film;
import com.example.entrevueSpringBoot.service.FilmService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = {"/film"})
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	private static final Logger logger =
			LoggerFactory.getLogger(FilmController.class);
	
	@Operation(summary = "Get list of all films")
	@GetMapping
	public List<Film> getAll() {
		return this.filmService.getAll();
	}
	
	@Operation(summary = "Get a Film by its ID")
	@GetMapping("/{id}")
	public Film getById(@PathVariable long id) {
		Film film = this.filmService.getFilmById(id);
		
		if (film == null) {
			throw new FilmNotFoundException("id-" + id);
		}
		
		return film;
	}
	
	@Operation(summary = "Create a new a film")
	@PostMapping
	public ResponseEntity<Film> create(@RequestBody Film film) {
		
		if (film == null)
			return ResponseEntity.noContent().build();
		
		Film savedFilm = this.filmService.save(film);
		
		logger.info("Film created: {}", savedFilm.toString());
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedFilm.getId()).toUri();
		
		return ResponseEntity.created(location).body(savedFilm);
	}

}
