package com.denilson.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.denilson.bookstore.domain.Book;
import com.denilson.bookstore.dtos.BookDTO;
import com.denilson.bookstore.service.BookService;

@RestController
@RequestMapping("/books")
public class BookResource {

	@Autowired
	private BookService service;

	// Método para a Procura pelo Id de livro.
	@GetMapping("/{id}")
	public ResponseEntity<Book> findById(@PathVariable Integer id) {
		Book obj = service.findById(id);

		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<BookDTO>> findAll(
			@RequestParam(value = "category", defaultValue = "0") Integer id_cat) {
		// localhost:8080/livros?categoria=1
		List<Book> list = service.findAll(id_cat);
		List<BookDTO> listDTO = list.stream().map(obj -> new BookDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	// Atualiza toda a informação da entidade
	@PutMapping(value = "/{id}")
	public ResponseEntity<Book> update(@Valid @PathVariable Integer id, @RequestBody Book obj) {
		Book newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	// Atualiza somente uma informação da entidade
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Book> updatePatch(@Valid @PathVariable Integer id, @RequestBody Book obj) {
		Book newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}

	@PostMapping
	public ResponseEntity<Book> create(@Valid @RequestParam(value = "category", defaultValue = "0") Integer id_cat,
			@RequestBody Book obj) {
		Book newObj = service.create(id_cat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/books/{id}")
				.buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Book> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
