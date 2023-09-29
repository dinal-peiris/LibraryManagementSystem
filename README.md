# LibraryManagementSystem
Librarry managementsystem with  book and members

Introduction 

This project demonstrate simple LibraryManagementSystem consists of two tables Books and Members.
Relationship between Book and Member Member can borrow many books. so there is Many to many relationship is between Books and Member
Many to many configurations is using anotations.

Suported operations 

1. CRUD operations for Book
2. CRUD operations for Member
3. Option to borrow and return books

Other Options

API key based security is configured currently working with all end points and need further enhansement to exclude unwanted URLS.

Project setup


1 Need to installed JDK17, Postgresql and Prefered IDE(Eclipse used) with maven support

Get clone from this URL https://github.com/dinal-peiris/LibraryManagementSystem.git and open using the ide.

2 Need to crete user account in postgresql and update the property file with username and password.

3.Sample data will be loaded when project is first run


Technology

    Java 17
    Spring Boot 3 (with Spring Web MVC, Spring Data JPA,Spring Security)
    PostgreSQL/JPA
    Maven
