package com.librarysystem.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarysystem.exception.ResourceNotFoundException;
import com.librarysystem.model.Book;
import com.librarysystem.model.Memeber;
import com.librarysystem.repository.BookRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	BookRepository BookRepository;

	@GetMapping("/Books")
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
		try {
			List<Book> Books = new ArrayList<Book>();

			if (title == null)
				BookRepository.findAll().forEach(Books::add);
			else
				BookRepository.findByTitleContaining(title).forEach(Books::add);

			if (Books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
		Optional<Book> BookData = BookRepository.findById(id);

		if (BookData.isPresent()) {
			return new ResponseEntity<>(BookData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Books")
	public ResponseEntity<Book> createBook(@RequestBody Book Book) {
		try {
			Book _Book = BookRepository
					.save(new Book(Book.getTitle(), Book.getAuthor(), false,Book.getIsbnNumber(),Book.getPublishedDate()));
			return new ResponseEntity<>(_Book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book Book) {
		Optional<Book> BookData = BookRepository.findById(id);

		if (BookData.isPresent()) {
			Book _Book = BookData.get();
			_Book.setTitle(Book.getTitle());
			_Book.setAuthor(Book.getAuthor());
			_Book.setPublishedDate(Book.getPublishedDate());
			return new ResponseEntity<>(BookRepository.save(_Book), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Books/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
		try {
			BookRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Books")
	public ResponseEntity<HttpStatus> deleteAllBooks() {
		try {
			BookRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	 @PostMapping("/Books/{bookId}/borrow")
	  public ResponseEntity<Book> borrowBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Memeber member) {
	    Book _book = BookRepository.findById(bookId).map(book -> {
	      long memId = member.getId();
	      
	    
	      
	      
	      // crreate new book loan
	     // book.borrowBook(member);
	      book.borrowBook(member);
	      return BookRepository.save(book);
	    }).orElseThrow(() -> new ResourceNotFoundException("Not found book with id = " + bookId));

	    return new ResponseEntity<>(_book, HttpStatus.CREATED);
	  }

	 
	 @PostMapping("/Books/{bookId}/Member/return")
	  public ResponseEntity<Book> retunBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Memeber member) {
	    Book _book = BookRepository.findById(bookId).map(book -> {
	      long memId = member.getId();
	      
	      
	      
	      
	    
	      book.returnBook(member);
	      return BookRepository.save(book);
	    }).orElseThrow(() -> new ResourceNotFoundException("Not found book with id = " + bookId));

	    return new ResponseEntity<>(_book, HttpStatus.CREATED);
	  }
	

}
