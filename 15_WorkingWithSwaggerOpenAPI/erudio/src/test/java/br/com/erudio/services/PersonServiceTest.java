package br.com.erudio.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.mapper.mocks.MockPerson;
import br.com.erudio.repositories.PersonRepository;

@TestInstance(Lifecycle.PER_CLASS)
//@ExtendWith(MockitoException.class)
class PersonServiceTest {
	
	MockPerson input;
	
	@InjectMocks
	private PersonService service;
	
	@Mock
	PersonRepository repository;
	
	@Mock
	ObjectMapper mapper;
	
	@Mock
	PersonMapper customMapper;

	@BeforeEach
	void setUp() throws Exception {
		this.input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testFindById() {
		Person person = input.mockEntity();
		when(repository.findById(0L)).thenReturn(Optional.of(person));
		when(mapper.convertValue(person, PersonVO.class)).thenReturn(input.mockVO());
		var result = service.findById(0l);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/0>;rel=\"self\"]"));
		assertEquals("Addres Test 0", result.getAddress());
		assertEquals("FirstName Test 0", result.getFirstName());
		assertEquals("LastName Test 0", result.getLastName());
		assertEquals("Male", result.getGender());
	}
	
	@Test
	void testFindByIdFail() {
		ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(null);
		});

		Assertions.assertEquals("No records found for this ID!", thrown.getMessage());
	}

	@Test
	void testFindAll() {
		List<Person> mockEntityList = input.mockEntityList();
		
		mockEntityList.stream().forEach(personEntity -> {
			PersonVO vo = input.mockVO(personEntity.getId().intValue());
			when(mapper.convertValue(personEntity, PersonVO.class)).thenReturn(vo);
		});			
		when(repository.findAll()).thenReturn(mockEntityList);
		
		List<PersonVO> findAllListVO = service.findAll();
		
		assertTrue(findAllListVO.size() > 0);				
		
		findAllListVO.stream().forEach(personVO ->{
			System.out.println(personVO.getLinks());			
			assertNotNull(personVO.getLinks());			              			
		});						        
		
	}


	@Test
	void testCreate() {
		Person entity = input.mockEntityBeforeSaved(25);
		
		Person persisted = entity;
		persisted.setId(25l);
		
		PersonVO vo = input.mockVOBeforeSaved(25);
		PersonVO voPersisted = input.mockVO(25);		
		
		
		when(customMapper.convertToEntity(vo)).thenReturn(entity);
		when(repository.save(entity)).thenReturn(persisted);
		when(mapper.convertValue(persisted, PersonVO.class)).thenReturn(voPersisted);
		
		
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/25>;rel=\"self\"]"));
		assertEquals("Addres Test 25", result.getAddress());
		assertEquals("FirstName Test 25", result.getFirstName());
		assertEquals("LastName Test 25", result.getLastName());
		assertEquals("Female", result.getGender());
		
	}
	
	@Test
	void testCreateFail() {
		Exception thrown = Assertions.assertThrows(Exception.class, () -> {
			service.create(null);
		});

		Assertions.assertEquals("Error saving Person!", thrown.getMessage());
	}

	@Test
	void testUpdate() {				
		PersonVO vo = input.mockVOToUpdate(25);
		Person entityFounded = input.mockEntity(25);
		Person entityUpdated = input.mockEntityToUpdate(25);
		
		when(repository.findById(25L)).thenReturn(Optional.of(entityFounded));
		when(repository.save(entityFounded)).thenReturn(entityUpdated);
		when(mapper.convertValue(entityUpdated, PersonVO.class)).thenReturn(vo);
		
		
		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/25>;rel=\"self\"]"));
		assertEquals("Addres Update Test 25", result.getAddress());
		assertEquals("FirstName Update Test 25", result.getFirstName());
		assertEquals("LastName Update Test 25", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testUpdateFail() {
		PersonVO vo = input.mockVOToUpdate(25);
		when(repository.findById(25l)).thenReturn(Optional.empty());
		ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			
			service.update(vo);
		});

		Assertions.assertEquals("No records found for this ID!", thrown.getMessage());
	}

	@Test
	void testDelete() {
		Person entityFounded = input.mockEntity(250);
		when(repository.findById(250l)).thenReturn(Optional.of(entityFounded));
		service.delete(250l);
	}

	@Test
	void testDeleteFail() {		
		when(repository.findById(505l)).thenReturn(Optional.empty());
		ResourceNotFoundException thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			
			service.delete(505l);
		});

		Assertions.assertEquals("No records found for this ID!", thrown.getMessage());
		
	}
}
