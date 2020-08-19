package com.memcached.service.common;

import java.io.Serializable;
import java.util.List;

public class PaginationResponse implements Serializable{
	private int total;
	private List<Books> books;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Books> getBooks() {
		return books;
	}

	public void setBooks(List<Books> books) {
		this.books = books;
	}

}
