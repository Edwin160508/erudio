package br.com.erudio.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.mapper.mocks.MockPerson;

public class JacksonMapperTest {
	
	ObjectMapper mapper;
	
	PersonMapper customMapper;
	
	MockPerson inputObject;
	
	@BeforeEach
	public void setUp() {
		mapper = new ObjectMapper();
		customMapper = new PersonMapper();
		inputObject = new MockPerson();
	}
	
	@Test
	public void parseEntityToVOTest() {
		PersonVO output = mapper.convertValue(inputObject.mockEntity(), PersonVO.class);
		assertEquals(Long.valueOf(0l), output.getKey());
		assertEquals("FirstName Test 0",output.getFirstName());
		assertEquals("LastName Test 0",output.getLastName());
		assertEquals("Addres Test 0",output.getAddress());
		assertEquals("Male",output.getGender());
	}
	
	@Test
	public void parseVOToEntityTest() {
		Person output = customMapper.convertToEntity(inputObject.mockVO());
		assertEquals(Long.valueOf(0l), output.getId());
		assertEquals("FirstName Test 0",output.getFirstName());
		assertEquals("LastName Test 0",output.getLastName());
		assertEquals("Addres Test 0",output.getAddress());
		assertEquals("Male",output.getGender());
	}
	
	@Test
	public void parseEntityListToVOListTest() {
		List<PersonVO> outputList = new ArrayList<>();
		List<Person> entityList = inputObject.mockEntityList();
		for(Person entity : entityList) {
			PersonVO vo = mapper.convertValue(entity, PersonVO.class);
			outputList.add(vo);
		}
		
		PersonVO outputZero = outputList.get(0);
		assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("FirstName Test 0", outputZero.getFirstName());
        assertEquals("LastName Test 0", outputZero.getLastName());
        assertEquals("Addres Test 0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());
		
        PersonVO outputSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("FirstName Test 7", outputSeven.getFirstName());
        assertEquals("LastName Test 7", outputSeven.getLastName());
        assertEquals("Addres Test 7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());
        
        PersonVO outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("FirstName Test 12", outputTwelve.getFirstName());
        assertEquals("LastName Test 12", outputTwelve.getLastName());
        assertEquals("Addres Test 12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
        
	}
	
	@Test
	public void parseVOListToEntityListTest() {
		List<Person> outputList = new ArrayList<>();
		List<PersonVO> voList = inputObject.mockVOList();
		for(PersonVO vo : voList) {
			Person entity = customMapper.convertToEntity(vo);
			outputList.add(entity);
		}
		
		Person outputZero = outputList.get(0);
		assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("FirstName Test 0", outputZero.getFirstName());
        assertEquals("LastName Test 0", outputZero.getLastName());
        assertEquals("Addres Test 0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());
		
        Person outputSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("FirstName Test 7", outputSeven.getFirstName());
        assertEquals("LastName Test 7", outputSeven.getLastName());
        assertEquals("Addres Test 7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());
        
        Person outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("FirstName Test 12", outputTwelve.getFirstName());
        assertEquals("LastName Test 12", outputTwelve.getLastName());
        assertEquals("Addres Test 12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
	}
}
