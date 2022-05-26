package com.residencia.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.exeption.NoSuchElementFoundException;
import com.residencia.academia.service.InstrutorService;

@RestController // responde a requisiçao rest
@RequestMapping("/instrutor") // o contexto da api
public class InstrutorController {
	@Autowired
	InstrutorService instrutorService;
	
	@GetMapping
	public ResponseEntity<List<Instrutor>> findAlInstrutor(){
		List<Instrutor> instrutorList = instrutorService.findAllInstrutor();
		if(instrutorList == null)
			throw new NoSuchElementFoundException("Não há nenhum instrutor");
		else
			return new ResponseEntity<>(instrutorService.findAllInstrutor(), HttpStatus.OK);
	}
	
	//do professor
	
	@GetMapping("/dto/{id}")
    public ResponseEntity<InstrutorDTO> findInstrutorDTOById(@PathVariable Integer id) {
        InstrutorDTO instrutorDTO = instrutorService.findInstrutorDTOById(id);
        if(null == instrutorDTO)
        	throw new NoSuchElementFoundException("Não foi possível encontrar InstrutorDTO com o id " + id + " pois não existe");
        else
           return new ResponseEntity<>(instrutorDTO, HttpStatus.OK);
    }
	
	//meu
	@GetMapping("/{id}")
	public ResponseEntity<Instrutor> findInstrutorById(@PathVariable Integer id) {
		Instrutor instrutor = instrutorService.findInstrutorById(id);
		if(null == instrutor) 
			throw new NoSuchElementFoundException("Não foi possível encontrar Instrutor com o id " + id + " pois não existe");
		else 
			return new ResponseEntity<>(instrutorService.findInstrutorById(id), HttpStatus.OK);
	}
	
	@PostMapping("/{dto}")
	public ResponseEntity<InstrutorDTO> saveInstrutorDTO(@RequestBody InstrutorDTO instrutorDTO){
		InstrutorDTO novoInstrutorDTO = instrutorService.saveInstrutorDTO(instrutorDTO);
		return new ResponseEntity<>(novoInstrutorDTO, HttpStatus.CREATED);
	}
	
	@PostMapping
	public ResponseEntity<Instrutor> saveInstrutor(@RequestBody Instrutor instrutor){
		Instrutor novoInstrutor = instrutorService.saveInstrutor(instrutor);
		return new ResponseEntity<>(novoInstrutor, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Instrutor> updateInstrutor (@RequestBody Instrutor instrutor){
		Instrutor novoInstrutor = instrutorService.updateInstrutor(instrutor);
		return new ResponseEntity<>(novoInstrutor, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInstrutor(@PathVariable Integer id){
		Instrutor instrutor = instrutorService.findInstrutorById(id);
		if(null == instrutor)
			throw new NoSuchElementFoundException("Não foi possível excluir Instrutor com o id " + id+ " pois não existe");
		
		instrutorService.deleteInstrutor(id);
		return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
	}
}
