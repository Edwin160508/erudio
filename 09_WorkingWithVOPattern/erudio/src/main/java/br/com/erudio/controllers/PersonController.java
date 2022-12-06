package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {	
	
	@Autowired
	private PersonService service;
	
	@GetMapping(value = "/{id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonVO> findById ( @PathVariable(value = "id")  Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonVO>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping( 
			consumes = MediaType.APPLICATION_JSON_VALUE ,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonVO> create(@RequestBody PersonVO person){
		return ResponseEntity.ok(service.create(person));
	}
	
	@PutMapping( 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonVO> update(@RequestBody PersonVO person){
		return ResponseEntity.ok(service.update(person));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete ( @PathVariable(value = "id")  Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
