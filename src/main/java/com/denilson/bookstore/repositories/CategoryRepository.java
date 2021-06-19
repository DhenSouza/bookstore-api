package com.denilson.bookstore.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.denilson.bookstore.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
