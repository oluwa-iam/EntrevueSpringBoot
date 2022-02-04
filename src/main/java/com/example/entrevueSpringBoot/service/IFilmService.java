package com.example.entrevueSpringBoot.service;

import java.util.List;

import com.example.entrevueSpringBoot.model.Film;

public interface IFilmService {

	/**
	 * Return the list of all Films
	 * @return the list
	 */
	List<Film> getAll();
	
	/**
	 * Return a specific film or Null, given an Id
	 * @param id the Id of the searched film
	 * @return the found Film or Null
	 */
	Film getFilmById(Long id);
	
	/**
	 * Save a film into the database
	 * @param film the film to save
	 * @return the saved Film
	 */
	Film save(Film film);

}
