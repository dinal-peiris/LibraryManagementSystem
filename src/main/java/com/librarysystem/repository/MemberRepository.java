package com.librarysystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarysystem.model.Memeber;


public interface MemberRepository extends JpaRepository<Memeber, Long> {
  
  List<Memeber> findByName(String title);
}
