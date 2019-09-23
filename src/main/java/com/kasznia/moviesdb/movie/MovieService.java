package com.kasznia.moviesdb.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    public List<Movie> getTopMovies(Integer limit) {
        return getAllMovies().stream()
                .sorted(Comparator.comparing(Movie::getRating).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public Optional<Movie> getMovie(Long id) {
        return movieRepository.findById(id);
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void updateMovie(Long id, Movie movie) {
        movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

}
