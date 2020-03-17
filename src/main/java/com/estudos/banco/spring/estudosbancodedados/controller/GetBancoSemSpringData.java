package com.estudos.banco.spring.estudosbancodedados.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudos.banco.spring.estudosbancodedados.domain.model.Cozinha;
import com.estudos.banco.spring.estudosbancodedados.domain.model.Restaurante;
import com.estudos.banco.spring.estudosbancodedados.jpa.CadastroCozinha;
import com.estudos.banco.spring.estudosbancodedados.jpa.CadastroRestaurante;


@RestController
@RequestMapping("/getbanco-de-dados")
public class GetBancoSemSpringData {
	
	@Autowired
	CadastroCozinha cadastroCozinha;
	
	@Autowired
	CadastroRestaurante cadastroRestaurante;
	
	
	@GetMapping("/sem-spring-data-cozinha")
	public ResponseEntity<List<Cozinha>> getCozinhaSemSpringData(){
		
		List<Cozinha> cozinhas = cadastroCozinha.listar();
		
		return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
	}
	
	@GetMapping("/sem-spring-data-restaurante")
	public ResponseEntity<List<Restaurante>> getRestauranteSemSpringData(){
		
		List<Restaurante> restaurantes = cadastroRestaurante.listar();
		return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
		
	}
}
