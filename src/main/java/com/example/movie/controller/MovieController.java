/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 */

// Write your code here
package com.example.movie.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.movie.model.Movie;
import com.example.movie.service.MovieH2Service;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class MovieController{

    @Autowired
    public MovieH2Service service;

    @GetMapping("/movies")
    public ArrayList<Movie> getMovies(){
        return service.getMovies();
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie){
        return service.addMovie(movie);
    }
}