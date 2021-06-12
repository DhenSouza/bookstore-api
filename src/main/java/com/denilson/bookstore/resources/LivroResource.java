package com.denilson.bookstore.resources;

import com.denilson.bookstore.domain.Livro;
import com.denilson.bookstore.dtos.LivroDTO;
import com.denilson.bookstore.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
}
