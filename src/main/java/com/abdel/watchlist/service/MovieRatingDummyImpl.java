package com.abdel.watchlist.service;

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "app.environment",havingValue ="dev")
public class MovieRatingDummyImpl implements MovieRatingService {

	@Override
	public Optional<String> getMovieRating(String title) {
		
		return Optional.of("9.999") ;
	}

}
