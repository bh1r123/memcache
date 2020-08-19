package com.memcached.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.memcached.service.common.PaginationResponse;

@Service
public class BooksService {

	@Autowired
	RestTemplate restTemplate;

	public PaginationResponse fetchAllBooks() {
		try {
			System.out.println("Data Captured on single instance");
			ResponseEntity<PaginationResponse> exchange = restTemplate.exchange("http://localhost:9090/api/v1/books",
					HttpMethod.GET, null, PaginationResponse.class);
			if (exchange.getStatusCode() == HttpStatus.OK) {
				return exchange.getBody();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
