package com.denilson.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.denilson.bookstore.domain.Livro;
import com.denilson.bookstore.dtos.LivroDTO;
import com.denilson.bookstore.service.LivroService;

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

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat) {
        // localhost:8080/livros?categoria=1
        List<Livro> list = service.findAll(id_cat);
        List<LivroDTO> listDTO = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    
    // Atualiza toda a informação da entidade
    @PutMapping(value="/{id}")
    public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro obj) {
    	Livro newObj = service.update(id, obj);
    	return ResponseEntity.ok().body(newObj);
    }
    // Atualiza somente uma informação da entidade
    @PatchMapping(value="/{id}")
    public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro obj) {
    	Livro newObj = service.update(id, obj);
    	return ResponseEntity.ok().body(newObj);
    }
    
    @PostMapping
    public ResponseEntity<Livro> create(@RequestParam(value="categoria", defaultValue = "0") Integer id_cat, @RequestBody Livro obj) {
    	Livro newObj = service.create(id_cat, obj);
    	URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
    	return ResponseEntity.created(uri).build();
    }
}
