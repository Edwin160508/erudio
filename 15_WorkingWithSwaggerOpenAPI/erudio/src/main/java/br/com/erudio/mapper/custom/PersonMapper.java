package br.com.erudio.mapper.custom;

import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;

@Service
public class PersonMapper {
	public Person convertToEntity (PersonVO person) {
		Person entity = new Person();
		entity.setId(person.getKey());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAddress(person.getAddress());		
		return entity;
	}
}
