package com.denilson.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denilson.bookstore.domain.Category;
import com.denilson.bookstore.domain.Book;
import com.denilson.bookstore.repositories.BookRepository;
import com.denilson.bookstore.service.exceptions.ObjectNotFoundException;

//Criar uma dependencia
@Service
public class BookService {
	// Vai informar ao spring que ira criar e gerenciar a instancia.
	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryService categoryService;

	public Book findById(Integer id) {
		Optional<Book> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Book.class.getName()));
	}

	public List<Book> findAll(Integer id_cat) {
		categoryService.findById(id_cat);
		return repository.findAllByCategory(id_cat);
	}

	public Book update(Integer id, Book obj) {
		Book newObj = findById(id);
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Book newObj, Book obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setNome_autor(obj.getNome_autor());
		newObj.setTexto(obj.getTexto());
	}

	public Book create(Integer id_cat, Book obj) {
		obj.setId(null);
		Category cat = categoryService.findById(id_cat);
		obj.setCategoria(cat);
		return repository.save(obj);
	}

	public void delete(Integer id) {
		Book obj = findById(id);
		repository.delete(obj);
	}
}
