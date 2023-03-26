package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class MovieRepository {
    HashMap<String,Movie> movieDb = new HashMap<>();
    HashMap<String,Director> directorDb = new HashMap<>();
    HashMap<String, ArrayList<String>> movieDirectorPairDb = new HashMap<>();

    public void addMovie(Movie movie){
        movieDb.put(movie.getName(),movie);
    }
    public void addDirector(Director director){
        directorDb.put(director.getName(),director);
        movieDirectorPairDb.put(director.getName(),new ArrayList<>());
    }
    public void addMovieDirectorPair(String movie,String director){
        if(!movieDirectorPairDb.containsKey(director))
            movieDirectorPairDb.put(director,new ArrayList<>());
        movieDirectorPairDb.get(director).add(movie);
    }
    public Movie getMovieByName(String name){
        return movieDb.get(name);
    }
    public Director getDirectorByName(String name){
        return directorDb.get(name);
    }
    public ArrayList<String> getMoviesByDirectorName(String name){
        return movieDirectorPairDb.get(name);
    }
    public ArrayList<String> findAllMovies(){
        ArrayList<String> movies = new ArrayList<>();
        for(Movie movie: movieDb.values()){
            movies.add(movie.getName());
        }
        return movies;
    }
    public void deleteDirectorByName(String name){
        directorDb.remove(name);
        for(String movie: movieDirectorPairDb.get(name)){
            movieDb.remove(movie);
        }
        movieDirectorPairDb.remove(name);
    }
    public void deleteAllDirectors(){
        directorDb.clear();
        for(ArrayList<String> movies : movieDirectorPairDb.values()){
            for(String movie: movies){
                movieDb.remove(movie);
            }
        }
        movieDirectorPairDb.clear();
    }
}
