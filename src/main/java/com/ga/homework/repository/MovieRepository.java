package com.ga.homework.repository;

import com.ga.homework.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    Movie findByName(String movieName);
    List<Movie> findByCategoryId(Long categoryId);

}
