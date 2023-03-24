package com.cerveja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cerveja.model.Beer;
import com.cerveja.repository.BeerRepository;

@RestController
public class BeerController {
	
	@Autowired
	private BeerRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Beer>> getAll(){
		
		return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
	}
	
}
