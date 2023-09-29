package com.librarysystem.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarysystem.model.Memeber;
import com.librarysystem.repository.MemberRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MemberController {

	@Autowired
	MemberRepository memberRepository;

	@GetMapping("/Member")
	public ResponseEntity<List<Memeber>> getAllMemebers(@RequestParam(required = false) String name) {
		try {
			List<Memeber> Member = new ArrayList<Memeber>();

			if (name == null)
				memberRepository.findAll().forEach(Member::add);
			else
				memberRepository.findByName(name).forEach(Member::add);

			if (Member.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Member, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Member/{id}")
	public ResponseEntity<Memeber> getBookById(@PathVariable("id") long id) {
		Optional<Memeber> MemberData = memberRepository.findById(id);

		if (MemberData.isPresent()) {
			return new ResponseEntity<>(MemberData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/Member")
	public ResponseEntity<Memeber> createBook(@RequestBody Memeber memeber) {
		try {
			Memeber member = memberRepository
					.save(new Memeber (memeber.getName(),memeber.getEmail()));
			return new ResponseEntity<>(member, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/Member/{id}")
	public ResponseEntity<Memeber> updateBook(@PathVariable("id") long id, @RequestBody Memeber Memeber) {
		Optional<Memeber> memberData = memberRepository.findById(id);

		if (memberData.isPresent()) {
			Memeber member = memberData.get();
			member.setName(Memeber.getName());
			member.setEmail(Memeber.getEmail());
			
			return new ResponseEntity<>(memberRepository.save(member), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Member/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
		try {
			memberRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Member")
	public ResponseEntity<HttpStatus> deleteAllBooks() {
		try {
			memberRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



}
