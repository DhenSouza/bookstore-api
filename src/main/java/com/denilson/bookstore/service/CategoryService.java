package com.denilson.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.denilson.bookstore.domain.Category;
import com.denilson.bookstore.dtos.CategoryDTO;
import com.denilson.bookstore.repositories.CategoryRepository;
import com.denilson.bookstore.service.exceptions.DataIntegrityException;
import com.denilson.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public Category findById(Integer id) {
		Optional<Category> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Category create(Category obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Category update(Integer id, CategoryDTO objDto) {
		Category obj = findById(id);
		obj.setNome(objDto.getNome());
		obj.setDescricao(objDto.getDescricao());
		return repository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new  DataIntegrityException (
					"Categoria não pode ser deletada! Possui livros associados");
		}
	}

}