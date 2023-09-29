package com.librarysystem.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "lmember")
public class Memeber {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;
  
  @ManyToMany(fetch = FetchType.LAZY,
	      cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      },
	      mappedBy = "member")
	  @JsonIgnore
	  private Set<Book> books = new HashSet<>();

  


public Memeber() {

  }

  public Memeber(String name, String email) {
    this.name = name;
    this.email = email;
   
  }

  public long getId() {
    return id;
  }
  
  
  public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Set<Book> getBooks() {
	return books;
}

public void setBooks(Set<Book> books) {
	this.books = books;
}
@Override
  public String toString() {
    return "Book [id=" + id + ", name=" + name + ", email=" + email + "]";
  }

}
