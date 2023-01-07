package br.com.erudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erudio.controllers.BookController;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.custom.BookMapper;
import br.com.erudio.repositories.BookRepository;
import br.com.erudio.utils.ErudioUtils;
import jakarta.validation.Valid;

@Service
public class BookService {
	private Logger logger = Logger.getLogger(BookService.class.getName());
	
	@Autowired
	BookRepository repository;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private BookMapper customMapper;
	
	public List<BookVO> findAll(){
		logger.info("Fiding all books!");
		List<Book> allBooksEntity = repository.findAll();
		List<BookVO> allBooksVO = new ArrayList<>();
		for(Book entity : allBooksEntity) {
			BookVO vo = applyHateoas(entity.getId(), entity);						
			allBooksVO.add(vo);
		}
			
		
		return allBooksVO;	
	}
	
	public BookVO findById(Long id) {
		logger.info("Fiding one book!");
		Book entity = findBook(id);
		BookVO vo = applyHateoas(id, entity);
		return vo;
	}
	
	private Book findBook(Long id) {
		logger.info("Fiding one book!");
		return  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
	}
	
	private BookVO applyHateoas(Long id, Book bookFound) {
		BookVO vo = mapper.convertValue(bookFound, BookVO.class);
		vo.setLaunchDate(ErudioUtils.calendarToString(bookFound.getLaunchDate()));
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public BookVO create(@Valid BookVO bookVO) {
		logger.info("Creating one book!");
		try {
			var entity = customMapper.convertToEntity(bookVO);
			var savedEntity = repository.save(entity);
			return applyHateoas(savedEntity.getId(), savedEntity);
		}catch (Exception ex) {
    		ex.getStackTrace();
    		throw new RuntimeException("Error saving Book!");
    	}
	}
	
	public BookVO update(@Valid BookVO bookVO) throws ParseException {
		logger.info("Updating one book!");
		var entity = findBook(bookVO.getKey());
		entity.setAuthor(bookVO.getAuthor());
		entity.setLaunchDate(ErudioUtils.stringToCalendar(bookVO.getLaunchDate()));
		entity.setPrice(bookVO.getPrice());
		entity.setTitle(bookVO.getTitle());
		try {
			var savedEntity = repository.save(entity);
			return applyHateoas(savedEntity.getId(), savedEntity);
		}catch (Exception ex) {
    		ex.getStackTrace();
    		throw new RuntimeException("Error saving Book!");
    	}
		
	}
	
	public void delete(Long id) {
		logger.info("Deleting one book!");
		var entity = findBook(id);
		repository.delete(entity);
	}
}
