package br.com.erudio.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;

public class MockPerson {
	public List<Person> mockEntityList(){
		List<Person> entities = new ArrayList<>();
		int index = 0;
		while(index < 14) {
			entities.add(mockEntity(index));
			index++;
		}
		
		return entities;
	}
	
	public Person mockEntity() {
		return mockEntity(0);
	}
	
	public Person mockEntityBeforeSaved(Integer number) {
		Person entity = new Person();
		entity.setFirstName("FirstName Test "+number);
		entity.setLastName("LastName Test "+number);
		entity.setAddress("Addres Test "+number);
		entity.setGender((number % 2)==0 ? "Male":"Female");
		
		return entity;
	}
	
	public PersonVO mockVOBeforeSaved(Integer number) {
		PersonVO vo = new PersonVO();
		vo.setFirstName("FirstName Test "+number);
		vo.setLastName("LastName Test " + number);
		vo.setAddress("Addres Test "+number);
		vo.setGender((number % 2)==0 ? "Male":"Female");		
		return vo;
	}
	
	public Person mockEntity(Integer number) {
		Person entity = new Person();
		entity.setFirstName("FirstName Test "+number);
		entity.setLastName("LastName Test " + number);
		entity.setAddress("Addres Test "+number);
		entity.setGender((number % 2)==0 ? "Male":"Female");
		entity.setId(number.longValue());
		return entity;
	}
	
	public Person mockEntityToUpdate(Integer number) {
		Person entity = new Person();
		entity.setFirstName("FirstName Update Test "+number);
		entity.setLastName("LastName Update Test " + number);
		entity.setAddress("Addres Update Test "+number);
		entity.setGender((number % 2)==0 ? "Male":"Female");
		entity.setId(number.longValue());
		return entity;
	}
	
	public PersonVO mockVOToUpdate(Integer number) {
		PersonVO vo = new PersonVO();
		vo.setFirstName("FirstName Update Test "+number);
		vo.setLastName("LastName Update Test " + number);
		vo.setAddress("Addres Update Test "+number);
		vo.setGender((number % 2)==0 ? "Male":"Female");
		vo.setKey(number.longValue());
		return vo;
	}
	
	public PersonVO mockVO() {
		return mockVO(0);
	}
	
	public PersonVO mockVO(Integer number) {
		PersonVO vo = new PersonVO();
		vo.setFirstName("FirstName Test "+number);
		vo.setLastName("LastName Test " + number);
		vo.setAddress("Addres Test "+number);
		vo.setGender((number % 2)==0 ? "Male":"Female");
		vo.setKey(number.longValue());
		return vo;
	}
	
	public List<PersonVO> mockVOList(){
		List<PersonVO> entities = new ArrayList<>();
		int index = 0;
		while(index < 14) {
			entities.add(mockVO(index));
			index++;
		}
		
		return entities;
	}
}
