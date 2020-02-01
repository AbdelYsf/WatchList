package com.abdel.watchlist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.abdel.watchlist.service.WatchlistService;

@WebMvcTest
@RunWith(SpringRunner.class)
public class WatchListcontrollerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	private WatchlistService watchlistService;
	@Test
	public void testShowWatchlistItemForm() throws Exception {
		
		mockMvc.perform(get("/watchlistItemForm"))
		.andExpect(status().is2xxSuccessful())
		.andExpect(view().name("watchlistItemForm"))
		.andExpect(model().size(1))
		.andExpect(model().attributeExists("watchlistItem"));
	}
	
	@Test
	public void testSubmitWatchlistItemForm() throws Exception {
		mockMvc.perform(post("/watchlistItemForm"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/watchlist"));
	}
}
