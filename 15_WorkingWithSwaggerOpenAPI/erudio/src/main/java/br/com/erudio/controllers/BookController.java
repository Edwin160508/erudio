package br.com.erudio.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.services.BookService;
import br.com.erudio.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController {

	@Autowired
	private BookService service;
	
	@GetMapping(produces = { 
			MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	@Operation(summary="Find all Books", description = "Find all Books" , 
			tags = {"Book"},
			responses = { 
				@ApiResponse(description = "Success", responseCode = "200", 
					content = {
						@Content(
							mediaType = MediaType.APPLICATION_JSON,
							array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
						)
					}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	public ResponseEntity<List<BookVO>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping(value = "/{id}",produces = { 
			MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	@Operation(summary="Finds a Person", description = "Finds a Book" , 
		tags = {"Book"},
		responses = { 
			@ApiResponse(description = "Success", responseCode = "200", 
				content = @Content(schema = @Schema(implementation = BookVO.class))
			),			
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		}
	)
	public ResponseEntity<BookVO> findById(@PathVariable(value = "id") Long id){
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping(
			consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML } ,
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary="Adds a new Book", description = "Create a Book" , 
		tags = {"Book"},
		responses = { 
			@ApiResponse(description = "Success", responseCode = "200", 
				content = @Content(schema = @Schema(implementation = BookVO.class))
			),			
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),			
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		}
	)
	public ResponseEntity<BookVO> create (@RequestBody BookVO bookVO){
		return ResponseEntity.ok(service.create(bookVO));
	}
	
	@PutMapping(
			consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML } ,
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary="Updates a Book", description = "Update a Book" , 
		tags = {"Book"},
		responses = { 
			@ApiResponse(description = "Success", responseCode = "200", 
				content = @Content(schema = @Schema(implementation = BookVO.class))
			),			
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		}
	)
	public ResponseEntity<BookVO> update(@RequestBody BookVO bookVO) throws ParseException{
		return ResponseEntity.ok(service.update(bookVO));
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deletes a Book", description = "Delete a Book" , 
		tags = {"Book"},
		responses = { 			
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		}
	)
	public ResponseEntity<?> delete (@PathVariable(value = "id") Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
