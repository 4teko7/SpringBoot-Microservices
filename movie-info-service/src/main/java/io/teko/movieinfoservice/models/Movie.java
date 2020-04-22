package io.teko.movieinfoservice.models;

import java.util.HashMap;

public class Movie {

	private String movieId;
	private String name;
	private String description;
	

	public Movie() {
	}
	public Movie(String movieId, String name,String description) {
		this.movieId = movieId;
		this.name = name;
		this.description = description;
	}
	
	public void fillWithMovies(HashMap<String,Movie> movieMap) {
		movieMap.put("1", new Movie("1","Batman","Yarasa Adam : Batman"));
		movieMap.put("2", new Movie("1","superman","Celik Adam"));
		movieMap.put("3", new Movie("1","cukur","Turk Dizisi"));
		movieMap.put("4", new Movie("1","osmanli","Tarih anlatan bir belgesel"));
		movieMap.put("5", new Movie("1","ben superim","Ben uydurdum belki bu isimde bir film vardir"));
		movieMap.put("6", new Movie("1","ben efsaneyim","Zombilerin oldugu bir film"));
		movieMap.put("7", new Movie("1","John Wich","John Wich Cok iyi silah kullanan Birisi"));
		movieMap.put("8", new Movie("1","Yuzuklerin Efendisi","Yuzukler Imparatorlugu"));
		movieMap.put("9", new Movie("1","Lord Of Kings","Yuzukler Imparatorlugu"));
		movieMap.put("10", new Movie("1","Harry Potter","Harry Adinda bir cocuk var"));
	}

	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
