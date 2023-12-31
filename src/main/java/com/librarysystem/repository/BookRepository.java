package com.librarysystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarysystem.model.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findByTitleContaining(String title);
}
