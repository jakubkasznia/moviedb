package com.kasznia.moviesdb.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class MovieController {

    private static final Integer TOP_LIMIT = 3;

    @Autowired
    private MovieService movieService;

    @RequestMapping("/all-movies")
    public String getMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("top", movieService.getTopMovies(TOP_LIMIT));
        return "all-movies";
    }

    @RequestMapping("/add-movie")
    public String addMovie() {
        return "add-movie";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("movie") Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/all-movies";
    }

    @RequestMapping("/delete-movie/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/all-movies";
    }

    @RequestMapping("/edit-movie/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        model.addAttribute(movieService.getMovie(id).orElseThrow(EntityNotFoundException::new));
        return "edit-movie";
    }

    @RequestMapping("update-movie")
    public String updateMovie(@ModelAttribute("movie") Movie movie) {
        movieService.updateMovie(movie.getId(), movie);
        return "redirect:/all-movies";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Movie not found!")
    @ExceptionHandler({EntityNotFoundException.class})
    public void handleMovieNotFound() {

    }

}
