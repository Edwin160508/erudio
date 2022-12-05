package br.com.erudio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.services.MathService;

@RestController
public class MathController {	
	
	@Autowired
	private MathService service;
	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(@PathVariable(value="numberOne") String numberOne, 
			          @PathVariable(value="numberTwo") String numberTwo) throws Exception{
		return service.sum(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double subtraction(@PathVariable(value="numberOne") String numberOne, 
			          @PathVariable(value="numberTwo") String numberTwo) throws Exception{
		return service.subtraction(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/division/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double division(@PathVariable(value="numberOne") String numberOne, 
			          @PathVariable(value="numberTwo") String numberTwo) throws Exception{
		return service.division(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double multiplication(@PathVariable(value="numberOne") String numberOne, 
			          @PathVariable(value="numberTwo") String numberTwo) throws Exception{
		return service.multiplication(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/mean/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double mean(@PathVariable(value="numberOne") String numberOne, 
			          @PathVariable(value="numberTwo") String numberTwo) throws Exception{
		return service.mean(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/square-root/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double squareRoot(@PathVariable(value="numberOne") String numberOne, 
			          @PathVariable(value="numberTwo") String numberTwo) throws Exception{
		return service.squareRoot(numberOne, numberTwo);
	}
	
}
