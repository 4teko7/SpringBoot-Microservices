package io.teko.movieinfoservice.resources;

import java.util.HashMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.teko.movieinfoservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {
		private HashMap<String,Movie> movieMap = new HashMap<String,Movie>();
		private Movie movie = new Movie();
		
		
		@RequestMapping("/{movieId}")
		public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
			movie.fillWithMovies(movieMap);
			return movieMap.get(movieId);
		}
}
