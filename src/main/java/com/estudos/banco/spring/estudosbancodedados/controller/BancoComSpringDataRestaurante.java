package com.estudos.banco.spring.estudosbancodedados.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudos.banco.spring.estudosbancodedados.domain.model.Restaurante;
import com.estudos.banco.spring.estudosbancodedados.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/restaurante")
public class BancoComSpringDataRestaurante {
	
	@Autowired
	RestauranteService restauranteService;
	
	@GetMapping
	public ResponseEntity<List<Restaurante>> getRestauranteSpringData(){
		return ResponseEntity.status(HttpStatus.OK).body(restauranteService.listar());
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Restaurante>> buscaPorId(@PathVariable Long codigo){
		
		Optional<Restaurante> restaurante = restauranteService.buscaId(codigo);
		
		if (!restaurante.isPresent())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		else
			return ResponseEntity.status(HttpStatus.OK).body(restaurante);
	}

	@PostMapping
	public ResponseEntity<Restaurante> adicionaRestaurante(@RequestBody Restaurante restauranteInput){
		return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.grava(restauranteInput));
	}
	
	@PutMapping
	public ResponseEntity<Restaurante> alteraRestaurante(@RequestBody Restaurante restaurante){
		
		Restaurante rest = new Restaurante();
		rest = restauranteService.atualiza(restaurante);
		if (rest != null) {
			return ResponseEntity.status(HttpStatus.OK).body(rest);
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@PutMapping("/{idRestaurante}")
	public ResponseEntity<Restaurante> alteraPorId(@PathVariable Long idRestaurante, @RequestBody Restaurante restaurante){

		Restaurante rest = new Restaurante();
		
		rest = restauranteService.atualiza(idRestaurante, restaurante);
		
		if (rest != null) {
			return ResponseEntity.status(HttpStatus.OK).body(rest);
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}	
	}
	
	@DeleteMapping
	public ResponseEntity<Object> deleta(@RequestBody Restaurante restaurante){
		
		restauranteService.deleta(restaurante);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("/{idRestaurante}")
	public ResponseEntity<Object> deletaPorId(@PathVariable Long idRestaurante){
		
		restauranteService.deleta(idRestaurante);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	

	
	@PatchMapping("/{idRestaurante}")
	public ResponseEntity<?> alteracaoParcial(@PathVariable Long idRestaurante, @RequestBody Map<String, Object> campos){
		
		Optional<Restaurante> restauranteAtual = restauranteService.buscaId(idRestaurante);
		
		if (!restauranteAtual.isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		merge(campos, restauranteAtual);
		
		Restaurante restauranteRetorno = new Restaurante();
		
		restauranteRetorno = restauranteService.atualiza(restauranteAtual.get());
				
		if (restauranteAtual.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(restauranteRetorno);
		}	
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	private void merge(Map<String, Object> dadosOrigem, Optional<Restaurante> restauranteAtual) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem,Restaurante.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			ReflectionUtils.setField(field, restauranteAtual.get(), novoValor);
		});
	}
}
