package com.estudos.banco.spring.estudosbancodedados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//A anotação @SpringBootApplication é uma anotação de conveniência que contém as seguintes 
//anotações do Spring: @Configuration, @EnableAutoConfiguration e @ComponentScan. 
//Essas duas últimas, basicamente, dizem ao inicializador do Spring: 
//“Busque e instancie todo bean anotado deste pacote para frente”.
@SpringBootApplication
public class EstudosBancoDeDadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstudosBancoDeDadosApplication.class, args);
	}

}
