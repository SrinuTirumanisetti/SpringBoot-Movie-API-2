/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.movie.service;

import org.springframework.web.bind.annotation.*;
import com.example.movie.model.MovieRowMapper;
import com.example.movie.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import com.example.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieH2Service implements MovieRepository{
    
    @Autowired
    public JdbcTemplate db;

    @Override
    public ArrayList<Movie> getMovies(){
        Collection<Movie> movieList = db.query("SELECT * FROM MOVIELIST",new MovieRowMapper());
        return new ArrayList<>(movieList);
    }


}