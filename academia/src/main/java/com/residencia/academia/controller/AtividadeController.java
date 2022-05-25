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

import com.residencia.academia.entity.Atividade;
import com.residencia.academia.exeption.NoSuchElementFoundException;
import com.residencia.academia.service.AtividadeService;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

	@Autowired
	private AtividadeService atividadeService;
	
	@GetMapping
	public ResponseEntity<List<Atividade>> findAllAtividade(){
		return new ResponseEntity<>(atividadeService.findAllAtividade(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Atividade> findAtividadeById(@PathVariable Integer id){
		Atividade atividade = atividadeService.findAtividadeId(id);
		if(null == atividade)
			throw new NoSuchElementFoundException("A atividade com o id " + id + " não existe");
		else
			return new ResponseEntity<>(atividadeService.findAtividadeId(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Atividade> saveAtividade(@RequestBody Atividade atividade){
		return new ResponseEntity<>(atividadeService.saveAtividade(atividade), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Atividade> updateAtividade(@RequestBody Atividade atividade){
		return new ResponseEntity<>(atividadeService.updateAtividade(atividade), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAtividade(@PathVariable Integer id){
		Atividade atividade = atividadeService.findAtividadeId(id);
		if(null == atividade)
			throw new NoSuchElementFoundException("Não foi possível deletar atividade com o id " + id+ " pois não existe");
		
		atividadeService.deleteAtividade(id);
		return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
	}
}
