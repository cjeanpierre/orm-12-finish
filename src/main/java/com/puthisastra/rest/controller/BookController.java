package com.puthisastra.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puthisastra.rest.domain.Book;
import com.puthisastra.rest.domain.Person;
import com.puthisastra.rest.repository.BookRepository;
import com.puthisastra.rest.repository.PersonRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api")
@Api(value="books", description="Books REST endpoint")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	private List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();
    	Book book1 = new Book();
    	book1.setId(1L);
    	book1.setTitle("John Wick");
    	book1.setPages(200);
    	Book book2 = new Book();
    	book2.setId(2L);
    	book2.setTitle("Avengers");
    	book2.setPages(300);
    	books.add(book1);
    	books.add(book2);
    	return books;
	}
	
	@GetMapping("/v1/books")
	public ResponseEntity<List<Book>> getV1Books() {
	    return ResponseEntity.ok(bookRepository.findAll());
	}

    @GetMapping("/v2/books")
    public ResponseEntity<List<Book>> getV2Books() {
    	return ResponseEntity.ok(getBooks());
    }
	
    @GetMapping(value = "/booksparams", params = "version=1")
    @ApiImplicitParam(dataType = "integer", name="version", allowableValues = "1,2")
    public ResponseEntity<List<Book>> paramV1() {
      return ResponseEntity.ok(bookRepository.findAll());
    }

    @ApiImplicitParam(dataType = "integer", name="version", allowableValues = "1,2")
    @GetMapping(value = "/booksparams", params = "version=2")
    public ResponseEntity<List<Book>> paramV2() {
    	return ResponseEntity.ok(getBooks());
    }
    
    @ApiImplicitParam(dataType = "integer", name="Accept-version", allowableValues = "1,2")
    @GetMapping(value = "/booksheader", headers = "Accept-version=1")
    public ResponseEntity<List<Book>> headerV1() {
    	return ResponseEntity.ok(bookRepository.findAll());
    }

    @ApiImplicitParam(dataType = "integer", name="Accept-version", allowableValues = "1,2")
    @GetMapping(value = "/booksheader", headers = "Accept-version=2")
    public ResponseEntity<List<Book>> headerV2() {
    	return ResponseEntity.ok(getBooks());
    }
    
    @GetMapping(value = "/booksmedia", produces = "application/vnd.books.app-v1+json")
    public ResponseEntity<List<Book>> producesV1() {
      return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping(value = "/booksmedia", produces = "application/vnd.books.app-v2+json")
    public ResponseEntity<List<Book>> producesV2() {
      return ResponseEntity.ok(getBooks());
    }
}
