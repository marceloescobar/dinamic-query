package com.mescobar.dinamicquery.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mescobar.dinamicquery.model.Person;
import com.mescobar.dinamicquery.query.PersonSearchParameter;
import com.mescobar.dinamicquery.service.PersonService;

@RestController
public class PersonController {

	private PersonService personService;
	
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	
	@PostMapping("/dynamic-search")
	ResponseEntity<List<Person>> getUserWithDynamicParam(@RequestBody PersonSearchParameter searchParameter){

		return ResponseEntity.ok(personService.getPersonWithDynamicParam(searchParameter));
	}
}
