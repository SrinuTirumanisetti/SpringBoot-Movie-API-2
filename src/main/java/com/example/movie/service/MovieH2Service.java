package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import com.example.movie.model.Movie;
import com.example.movie.model.MovieRowMapper;
import com.example.movie.repository.MovieRepository;

@Service
public class MovieH2Service implements MovieRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Movie> getMovies() {
        List<Movie> movies = db.query("SELECT * FROM MOVIELIST", new MovieRowMapper());
        return new ArrayList<>(movies);
    }

    @Override
    public Movie addMovie(Movie movie) {
        db.update("INSERT INTO MOVIELIST(movieName, leadActor) VALUES(?, ?)",
                movie.getMovieName(), movie.getLeadActor());

        Movie savedMovie = db.queryForObject(
                "SELECT * FROM MOVIELIST ORDER BY movieId DESC LIMIT 1",
                new MovieRowMapper()
        );
        return savedMovie;
    }

    @Override
    public Movie getMovieById(int movieId) {
        try {
            return db.queryForObject(
                    "SELECT * FROM MOVIELIST WHERE movieId = ?",
                    new MovieRowMapper(),
                    movieId
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
    }

    @Override
    public Movie updateMovie(int movieId, Movie movie) {
        if (movie.getMovieName() != null) {
            db.update("UPDATE MOVIELIST SET movieName = ? WHERE movieId = ?",
                    movie.getMovieName(), movieId);
        }
        if (movie.getLeadActor() != null) {
            db.update("UPDATE MOVIELIST SET leadActor = ? WHERE movieId = ?",
                    movie.getLeadActor(), movieId);
        }
        return getMovieById(movieId);
    }

    @Override
    public void deleteMovie(int movieId) {
        db.update("DELETE FROM MOVIELIST WHERE movieId = ?", movieId);
    }
}
