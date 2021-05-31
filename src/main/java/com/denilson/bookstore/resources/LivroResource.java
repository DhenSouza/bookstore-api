package com.denilson.bookstore.resources;

import com.denilson.bookstore.domain.Livro;
import com.denilson.bookstore.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroResource {

    @Autowired
    private LivroService service;

    // Método para a Procura pelo Id de livro.
    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id) {
    Livro obj = service.findById(id);

    return ResponseEntity.ok().body(obj);
    }
}
