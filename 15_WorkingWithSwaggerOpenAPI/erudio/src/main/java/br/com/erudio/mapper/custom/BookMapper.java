package br.com.erudio.mapper.custom;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.utils.ErudioUtils;

@Service
public class BookMapper {
	
	public Book convertToEntity (BookVO vo) throws ParseException {
		Book entity = new Book();
		entity.setId(vo.getKey());
		entity.setAuthor(vo.getAuthor());
		entity.setLaunchDate(ErudioUtils.stringToCalendar(vo.getLaunchDate()));
		entity.setPrice(vo.getPrice());
		entity.setTitle(vo.getTitle());
		return entity;
	}
}
