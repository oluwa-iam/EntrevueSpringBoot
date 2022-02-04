package com.example.entrevueSpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entrevueSpringBoot.model.Film;
import com.example.entrevueSpringBoot.repository.FilmRepository;

@Component
public class FilmService implements IFilmService {
	
	@Autowired
	private FilmRepository filmRepoitory;
	
	public List<Film> getAll() {
		return this.filmRepoitory.findAll();
	}
	
	public Film getFilmById(Long id) {
		return this.filmRepoitory.findById(id).orElse(null);
	}

	public Film save(Film film) {
		return this.filmRepoitory.save(film);
	}
}
