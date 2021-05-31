package com.denilson.bookstore.service;

import com.denilson.bookstore.domain.Livro;
import com.denilson.bookstore.repositories.LivroRepository;
import com.denilson.bookstore.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Criar uma dependencia
@Service
public class LivroService {
    // Vai informar ao spring que ira criar e gerenciar a instancia.
    @Autowired
    private LivroRepository repository;

    public Livro findById(Integer id) {
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + ", Tipo: " + Livro.class.getName()));
    }

}
