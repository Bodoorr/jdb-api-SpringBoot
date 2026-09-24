package com.ga.homework.controller;

import com.ga.homework.model.Movie;
import com.ga.homework.repository.MovieRepository;
import com.ga.homework.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class MovieController {
    private MovieService movieService;


    @PostMapping("/categories/{categoryId}/movies")
    public Movie createRecipe(
            @PathVariable(value = "categoryId") Long categoryId, @RequestBody Movie movieObject)
    {
        System.out.println("Calling createMovie ==>");
        return movieService.createMovie(categoryId, movieObject);
    }

    @GetMapping(path = "/categories/{categoryId}/movies")
    public List<Movie> getMovies(){
        System.out.println("calling getMovies() ==> ");
        return movieService.getMovies();
    }

    @GetMapping(path="/categories/{categoryId}/movies/{movieId}")
    public Movie getMovie(@PathVariable Long categoryId, @PathVariable Long movieId){
        System.out.println("calling getMovie ==>");
        return movieService.getMovie(movieId);
    }

    @PutMapping(path = "/categories/{categoryId}/movies/{movieId}")
    public Movie updateMovie(@PathVariable Long categoryId, @PathVariable Long movieId ,@RequestBody Movie movieObject){
        System.out.println("calling updateMovie ==>");
        return movieService.updateMovie(movieId,movieObject);
    }

    @DeleteMapping(path= "/categories/{categoryId}/movies/{movieId}")
    public Movie deleteMovie(@PathVariable(value = "categoryId") Long categoryId, @PathVariable(value = "movieId") Long movieId){
        System.out.println("calling deleteMovie ==>");
        return movieService.deleteMovie(movieId);
    }


}
