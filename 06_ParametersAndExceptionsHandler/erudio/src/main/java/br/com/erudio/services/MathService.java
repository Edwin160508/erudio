package br.com.erudio.services;

import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.utils.ErudioUtils;

@Service
public class MathService {
	
	public Double sum(String numberOne, String numberTwo) throws Exception{
		if(!ErudioUtils.isNumeric(numberOne) || !ErudioUtils.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		return ErudioUtils.convertToDouble(numberOne) + ErudioUtils.convertToDouble(numberTwo);
	}
	
	public Double subtraction(String numberOne, String numberTwo) throws Exception{
		if(!ErudioUtils.isNumeric(numberOne) || !ErudioUtils.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		return ErudioUtils.convertToDouble(numberOne) - ErudioUtils.convertToDouble(numberTwo);
	}
	
	
	public Double division(String numberOne, String numberTwo) throws Exception{
		if(!ErudioUtils.isNumeric(numberOne) || !ErudioUtils.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		return ErudioUtils.convertToDouble(numberOne) / ErudioUtils.convertToDouble(numberTwo);
	}
	
	
	public Double multiplication(String numberOne, String numberTwo) throws Exception{
		if(!ErudioUtils.isNumeric(numberOne) || !ErudioUtils.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		return ErudioUtils.convertToDouble(numberOne) * ErudioUtils.convertToDouble(numberTwo);
	}
	
	
	public Double mean(String numberOne, String numberTwo) throws Exception{
		if(!ErudioUtils.isNumeric(numberOne) || !ErudioUtils.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		return (ErudioUtils.convertToDouble(numberOne) + ErudioUtils.convertToDouble(numberTwo))/2;
	}
	
	
	public Double squareRoot(String numberOne, String numberTwo) throws Exception{
		if(!ErudioUtils.isNumeric(numberOne) || !ErudioUtils.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value.");
		}
		return Math.sqrt(ErudioUtils.convertToDouble(numberOne) + ErudioUtils.convertToDouble(numberTwo));
	}
}
