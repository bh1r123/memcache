package com.memcached.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.memcached.service.common.Books;
import com.memcached.service.common.Response;
import com.memcached.service.services.CachingService;

@RestController
public class CacheController {

	@Autowired
	CachingService cachingService;
	
	@GetMapping(value="/api/v1/responses")
	public ResponseEntity<List<Response>> fetchResponses(){
		return ResponseEntity.ok(cachingService.fetchAllResponses());
	}
	
	@GetMapping(value="/api/v1/books")
	public ResponseEntity<List<Books>> fetchBooks(){
		return ResponseEntity.ok(cachingService.fetchBooks());
	}
	
	@GetMapping(value="/api/v1/books/update")
	public ResponseEntity<String> booksUpdate(){
		Books book = new Books();
		book.setId(0);
		book.setName("Book name Updated");
		cachingService.updateBooks(book);
		return ResponseEntity.ok("Cache Updated..!");
	}
	
	@GetMapping(value="/api/v1/responses/delete")
	public ResponseEntity<String> deleteCache(){
		cachingService.deleteCache();
		return ResponseEntity.ok("Cache Deleted..!");
	}
	
}
