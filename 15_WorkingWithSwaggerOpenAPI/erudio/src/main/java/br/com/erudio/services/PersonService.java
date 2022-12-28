package br.com.erudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.repositories.PersonRepository;
import jakarta.validation.Valid;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private PersonMapper customMapper;
	
	public List<PersonVO> findAll() {
		
		logger.info("Fiding all peaple!");	
		
		List<Person> allPersonsEntity = repository.findAll();
		List<PersonVO> allPersonsVO = new ArrayList<>();
		
		for(Person entity : allPersonsEntity) {
			PersonVO vo = applyHateoas(entity.getId(), entity);
			allPersonsVO.add(vo);
		}
		
		return allPersonsVO;
	}
	
	public PersonVO findById(Long id) {
		
		logger.info("Fiding one person!");
		
		Person personFound = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		PersonVO vo = applyHateoas(id, personFound);
		
		return vo;
	}

	private PersonVO applyHateoas(Long id, Person personFound) {
		PersonVO vo = mapper.convertValue(personFound, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(@Valid PersonVO personVO) {	
		
		logger.info("Creating one person!");
		try {
			Person entity = customMapper.convertToEntity(personVO);
			Person savedEntity = repository.save(entity);
			return applyHateoas(savedEntity.getId(), savedEntity);
		}catch (Exception ex) {
    		ex.getStackTrace();
    		throw new RuntimeException("Error saving Person!");
    	}
	}
	
	public PersonVO update(@Valid PersonVO personVO) {
		
		logger.info("Updating one person!");
		
		Person entity = repository.findById(personVO.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setAddress(personVO.getAddress());
		entity.setGender(personVO.getGender());
		try {
			var entitySaved = repository.save(entity);
			return applyHateoas(entitySaved.getId(), entitySaved);
		}catch (Exception ex) {
    		ex.getStackTrace();
    		throw new RuntimeException("Error to update Person!");
    	}
	}
	
	public void delete(Long id) {	
		logger.info("Deleting one person!");
		
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		repository.delete(entity);
	}
	
}
