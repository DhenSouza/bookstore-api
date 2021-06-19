package com.denilson.bookstore.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.denilson.bookstore.domain.Book;

public class BookDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Campo TITULO é requerido")
	@Length(min = 3, max = 50, message = "O campo TITULO deve ter entre 3 e 50 caracteres")
    private String titulo;

    public BookDTO() {
        super();
    }

    public BookDTO(Book obj) {
        super();
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
