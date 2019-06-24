package com.puthisastra.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puthisastra.rest.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	
}
