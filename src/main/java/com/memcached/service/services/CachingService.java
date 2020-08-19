package com.memcached.service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.memcached.service.common.Books;
import com.memcached.service.common.PaginationResponse;
import com.memcached.service.common.Response;

@Service
public class CachingService {

	@Autowired
	BooksService bookService;

	@Cacheable(value = "responses")
	public List<Response> fetchAllResponses() {
		System.err.println("No Cached is available");
		return getResponses();
	}

	private List<Response> getResponses() {
		ArrayList<Response> responses = new ArrayList<Response>();
		for (int i = 0; i < 10; i++) {
			Response response = new Response();
			response.setId("" + i);
			response.setName(String.valueOf((char) (67 + i)));
			responses.add(response);
		}
		return responses;
	}

	@Cacheable(value = "books",unless ="#result==null")
	public List<Books> fetchBooks() {
		return fetchAllBooks().getBooks();
	}

	private PaginationResponse fetchAllBooks() {
		return bookService.fetchAllBooks();
	}

	@CachePut(value = "books", key = "#book.id", unless = "#result==null")
	public Books updateBooks(Books book) {
		return book;
	}

	@CacheEvict(value="responses",allEntries=true)
	public void deleteCache() {

	}

}
