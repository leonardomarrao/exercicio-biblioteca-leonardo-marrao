package com.academia.biblioteca.academiabiblioteca;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event.ID;

@RestController
public class BookDbControler {
	
	@Autowired
	private AcademiaDaoService service;
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@GetMapping("/db/books")
	public List<Book> retrieveAllBooks(){
		return bookRepository.findAll();
		
	}
	
	@GetMapping("/db/book/{id}")
	public Optional<Book> retrieveLivro(@PathVariable int id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book == null)
			throw new BookNotFoundException("Não foi encontrado o Livro com o ID: " + id);
		
		return book;
	}	
	
	@DeleteMapping("/db/book/{id}")
	public void deleteBook(@PathVariable int id) {
		bookRepository.deleteById(id);
	}
	
	@PostMapping("/db/book")
	public void createBook(@RequestBody Book book) {
		Book savedBook = bookRepository.save(book);
	}
}
