package br.com.erudio.services;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	private ObjectMapper mapper;
	
	public List<PersonVO> findAll() {
		
		logger.info("Fiding all peaple!");	
		
		List<Person> allPersonsEntity = repository.findAll();
		List<PersonVO> allPersonsVO = new ArrayList<>();
		
		for(Person entity : allPersonsEntity) {
			PersonVO vo = mapper.convertValue(entity, PersonVO.class);
			allPersonsVO.add(vo);
		}
		
		return allPersonsVO;
	}
	
	public PersonVO findById(Long id) {
		
		logger.info("Fiding one person!");
		
		Person personFound = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		return mapper.convertValue(personFound, PersonVO.class);
	}
	
	public PersonVO create(@Valid PersonVO personVO) {	
		
		logger.info("Creating one person!");
		try {
			Person entity = mapper.convertValue(personVO, Person.class);
			Person savedEntity = repository.save(entity);
			return mapper.convertValue(savedEntity, PersonVO.class);
		}catch (Exception ex) {
    		ex.getStackTrace();
    		throw new RuntimeException("Error saving Person!");
    	}
	}
	
	public PersonVO update(@Valid PersonVO personVO) {
		
		logger.info("Updating one person!");
		
		Person entity = repository.findById(personVO.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setAddress(personVO.getAddress());
		entity.setGender(personVO.getGender());
		try {
			return mapper.convertValue(repository.save(entity), PersonVO.class);
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
