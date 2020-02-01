package com.abdel.watchlist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdel.watchlist.domain.WatchlistItem;
import com.abdel.watchlist.exceptions.DuplicateTitleException;
import com.abdel.watchlist.repository.WatchlistRepository;

@Service
public class WatchlistService {

	WatchlistRepository watchlistRepository;
	MovieRatingService movieRatingService;
	
	@Autowired
	public WatchlistService(WatchlistRepository watchlistRepository, MovieRatingService movieRatingService) {
		super();
		this.watchlistRepository = watchlistRepository;
		this.movieRatingService = movieRatingService;
	}

	public List<WatchlistItem> getWatchlistItems(){
		List<WatchlistItem> list = watchlistRepository.getList();
		for (WatchlistItem watchlistItem : list) {
			Optional<String> movieRating = movieRatingService.getMovieRating(watchlistItem.getTitle());
			if(movieRating.isPresent()) {
				watchlistItem.setRating(movieRating.get());
			}
		}
		return list;
	}
	
	public int getWatchlistItemsSize() {
		return watchlistRepository.getList().size();
	}
	
	public WatchlistItem findWatchlistItemById(Integer id) {
		return watchlistRepository.findById(id);
	}
	
	public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException {
		
		WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());
		
		if (existingItem == null) {
			if (watchlistRepository.findByTitle(watchlistItem.getTitle())!=null) {
				throw new DuplicateTitleException();
			}
			watchlistRepository.addItem(watchlistItem);
		} else {
			existingItem.setComment(watchlistItem.getComment());
			existingItem.setPriority(watchlistItem.getPriority());
			existingItem.setRating(watchlistItem.getRating());
			existingItem.setTitle(watchlistItem.getTitle());  
		}
	}
}
