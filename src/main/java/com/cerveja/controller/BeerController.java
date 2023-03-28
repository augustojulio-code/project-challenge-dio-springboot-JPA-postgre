package com.cerveja.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cerveja.model.Beer;
import com.cerveja.repository.BeerRepository;

@RestController
@RequestMapping("/")
public class BeerController {

	@Autowired
	private BeerRepository repository;

	/*
	 * @GetMapping("teste") public String test() { return "Olá imbecil"; }
	 */

	@GetMapping(value = "/cerveja")
	public ResponseEntity<List<Beer>> getAll() {

		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/cervejas")
	public ResponseEntity<Beer> saveBeer(@RequestBody Beer beer) {

		return new ResponseEntity<>(repository.save(beer), HttpStatus.CREATED);
	}

	@GetMapping("/cervejas/{id}")
	public ResponseEntity<Beer> getOneProduct(@PathVariable(value = "id") UUID id) {

		Optional<Beer> beer = repository.findById(id);
		if (beer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(beer.get(), HttpStatus.OK);
	}

	@DeleteMapping("/cervejas/{id}")
	public ResponseEntity<Beer> deleteProduct(@PathVariable(value = "id") UUID id) {

		Optional<Beer> beer = repository.findById(id);
		if (beer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		repository.delete(beer.get());
		return new ResponseEntity<>(beer.get(), HttpStatus.OK);
	}
	
	@PutMapping("/cervejas/{id}")
	public ResponseEntity<Beer> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody Beer beer) {

		Optional<Beer> oBeer = repository.findById(id);
		if (oBeer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(repository.save(beer),HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException ex){
		
		Map<String,String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldname = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldname, errorMessage);
		});
		
		return errors;
	}

}
