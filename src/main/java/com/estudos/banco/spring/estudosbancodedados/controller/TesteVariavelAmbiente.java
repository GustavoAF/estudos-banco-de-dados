package com.estudos.banco.spring.estudosbancodedados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.banco.spring.estudosbancodedados.applicationproperties.ApplicationConfigurationsBD;

@RestController
@RequestMapping("/variavies-de-ambiente")
public class TesteVariavelAmbiente{
	
	@Autowired
	ApplicationConfigurationsBD applicationConfigurationsBD;
	
	@GetMapping
	public ResponseEntity<String> variavelAmbiente(){
		
		String url = applicationConfigurationsBD.getDataSourceUrl();
		String user = applicationConfigurationsBD.getDataSourceUser();
		String password = applicationConfigurationsBD.getDataSourcePassword();
		
		return ResponseEntity.status(HttpStatus.OK).body("Olha eles ai url:"+ url + " - usuario: " + user + " - senha: " + password );
		
	}

}
