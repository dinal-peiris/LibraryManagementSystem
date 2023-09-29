package com.librarysystem.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;



import jakarta.persistence.*;

@Entity
@Table(name = "lbook")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  
@Column(name = "bookavailabilitystatus")
  private boolean bookAvailabilityStatus;
  
  @Column(name = "isbnnumnber")
  private String isbnNumber;

  @Column(name = "publisheddate")
  private Date publishedDate;
  
  @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "lib_books_member", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
      @JoinColumn(name = "member_id") })
  private Set<Memeber> member = new HashSet<>();
  

public Book() {

  }

  public Book(String title, String description, boolean availableStatus) {
    this.title = title;
    this.author = description;
    this.bookAvailabilityStatus = availableStatus;
  }

  public Book(String title, String author, boolean availableStatus,String isbnNum,Date publishedDate) {
	    this.title = title;
	    this.author = author;
	    this.bookAvailabilityStatus = availableStatus;
	    this.isbnNumber=isbnNum;
	    this.publishedDate=publishedDate;
	  }
  
  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isBookAvailabilityStatus() {
		return bookAvailabilityStatus;
	}

	public void setBookAvailabilityStatus(boolean bookAvailabilityStatus) {
		this.bookAvailabilityStatus = bookAvailabilityStatus;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	  public Set<Memeber> getMember() {
			return member;
		}

		public void setMember(Set<Memeber> member) {
			this.member = member;
		}

		 public void borrowBook(Memeber memeber) {
			    this.member.add(memeber);
			    memeber.getBooks().add(this);
			  }

			  public void returnBook(Memeber memeber) {
			    if (memeber != null) {
			      this.member.remove(memeber);
			      memeber.getBooks().remove(this);
			    }
			  }


  @Override
  public String toString() {
    return "Book [id=" + id + ", title=" + title + ", desc=" + author + ", published=" + bookAvailabilityStatus + "]";
  }



}
