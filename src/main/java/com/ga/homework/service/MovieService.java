package com.ga.homework.service;

import com.ga.homework.exception.InformationNotFoundException;
import com.ga.homework.model.Category;
import com.ga.homework.model.Movie;
import com.ga.homework.repository.CategoryRepository;
import com.ga.homework.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    private MovieRepository movieRepository;
    private CategoryRepository categoryRepository;

    public Movie createMovie(Long categoryId, Movie movie){
        System.out.println("Service calling createMovie ==>");
        Category category= categoryRepository.findById(categoryId).
                orElseThrow(()->
                        new InformationNotFoundException(
                                "Category with id "+categoryId+ " not found"
                        ));
        movie.setCategory(category);
        return movieRepository.save(movie);
    }

    public List<Movie> getMovies(){
        System.out.println("Service calling getMovies ==>");
        return movieRepository.findAll();
    }

    public Movie getMovie(Long movieId){
        System.out.println("service getMovie ==>");

        return movieRepository.findById(movieId).
                orElseThrow(()->
                        new InformationNotFoundException("movie with id "+ movieId + " not found"));
    }

    public Movie updateMovie(Long movieId, Movie movieObject){
        System.out.println("service calling updateMovie ==>");

        Movie existingMovie= movieRepository.findById(movieId).
                orElseThrow(()->
                        new InformationNotFoundException("movie with id "+movieId+" not found"));
        existingMovie.setName(movieObject.getName());
        existingMovie.setTime(movieObject.getTime());
        existingMovie.setYear(movieObject.getYear());
        existingMovie.setMovieCast(movieObject.getMovieCast());
        existingMovie.setPublish(movieObject.isPublish());

        return movieRepository.save(existingMovie);
    }

    public Movie deleteMovie(Long movieId){
        System.out.println("service is calling deleteMovie");

        Movie movie= movieRepository.findById(movieId)
                .orElseThrow(()->
                        new InformationNotFoundException("movie with id "+movieId+" not found"));
        movieRepository.delete(movie);
        return movie;
    }

}
