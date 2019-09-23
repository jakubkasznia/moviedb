package com.kasznia.moviesdb.movie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    private ArrayList<Movie> allMovies;

    private ArrayList<Movie> top3movies;

    @Before
    public void init() {
        Movie movie1 = new Movie(1L, "Title1", "Movie Description 1", "Name Surname", 3);
        Movie movie2 = new Movie(2L, "Title2", "Movie Description 2", "Name Surname", 5);
        Movie movie3 = new Movie(3L, "Title3", "Movie Description 3", "Name Surname", 8);
        Movie movie4 = new Movie(4L, "Title4", "Movie Description 4", "Name Surname", 6);
        Movie movie5 = new Movie(5L, "Title5", "Movie Description 5", "Name Surname", 1);

        allMovies = new ArrayList<>(Arrays.asList(movie1, movie2, movie3, movie4, movie5));
        top3movies = new ArrayList<>(Arrays.asList(movie3, movie4, movie2));
        MockitoAnnotations.initMocks(this);
        when(movieRepository.findAll()).thenReturn(allMovies);
    }

    @Test
    public void shouldReturnTop3() {
        assertEquals(movieService.getTopMovies(3), top3movies);
    }

}
