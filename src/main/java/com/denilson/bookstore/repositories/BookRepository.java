package com.denilson.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.denilson.bookstore.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    @Query("SELECT obj FROM Book obj WHERE obj.category.id = :id_cat ORDER BY titulo")
    List<Book> findAllByCategory(@Param(value = "id_cat") Integer id_cat);
}
