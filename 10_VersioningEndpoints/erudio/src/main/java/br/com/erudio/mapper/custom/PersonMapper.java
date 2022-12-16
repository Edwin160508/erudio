package br.com.erudio.mapper.custom;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v2.PersonVOV2;

@Service
public class PersonMapper {
	
	public PersonVOV2 convertToVOV2 (Person entity) {
		PersonVOV2 vo2 = new PersonVOV2();
		vo2.setId(entity.getId());
		vo2.setFirstName(entity.getLastName());
		vo2.setLastName(entity.getLastName());
		vo2.setGender(entity.getGender());
		vo2.setAddress(entity.getAddress());
		vo2.setBirthDay(LocalDateTime.now().toString());
		return vo2;
	}
	
	public Person convertToEntity (PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAddress(person.getAddress());		
		return entity;
	}
}
