package com.residencia.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.service.InstrutorService;

@RestController // responde a requisiçao rest
@RequestMapping("/instrutor") // o contexto da api
public class InstrutorController {
	@Autowired
	InstrutorService instrutorService;
	
	@GetMapping
	public ResponseEntity<List<Instrutor>> findAlInstrutor(){
		List<Instrutor> instrutorList = instrutorService.findAllInstrutor();
		return new ResponseEntity<>(instrutorList, HttpStatus.OK);
		//return  ResponseEntity.ok().body(instrutorList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Instrutor> findInstrutorById(@PathVariable Integer id) {
		//return new ResponseEntity<>(instrutorService.findInstrutorById(id), HttpStatus.OK);
		Instrutor instrutor = instrutorService.findInstrutorById(id);
		return new ResponseEntity<>(instrutor, HttpStatus.OK);
	}
}
