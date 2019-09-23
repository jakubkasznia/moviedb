package com.kasznia.moviesdb.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class RestMovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable Long id) {
        return movieService.getMovie(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping("/movies")
    public void addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/movies/{id}")
    public void updateMovie(@RequestBody Movie movie, @PathVariable Long id) {
        movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Movie not found!")
    @ExceptionHandler({EntityNotFoundException.class})
    public void handleMovieNotFound() {

    }

}
