package com.estudos.banco.spring.estudosbancodedados.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estudos.banco.spring.estudosbancodedados.domain.model.Restaurante;
import com.estudos.banco.spring.estudosbancodedados.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	public List<Restaurante> listar(){
		
		return (List<Restaurante>)  restauranteRepository.findAll();
	}
	
	public Restaurante grava(Restaurante  restauranteAtual) {
		
		return restauranteRepository.save(restauranteAtual);
	}
	
	public Optional<Restaurante> buscaId(Long id){	
		
		return restauranteRepository.findById(id);
	}
	
	public void deleta(Restaurante restaurante) {	
		
		restauranteRepository.delete(restaurante);
	}
	
	public void deleta(Long id) {
		
		restauranteRepository.deleteById(id);
	}
	
	public Restaurante atualiza(Restaurante restaurante) {	
		
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restaurante.getId());
		
		if (restauranteAtual.isPresent()) {
			BeanUtils.copyProperties(restaurante,restauranteAtual, "id");	// A Classe abstrata BeanUtils que faz parte do pacote org.springframework.beans		
			restauranteRepository.save(restaurante);                        //possui o método copyProperties, que substitui os valores das propriedades
			return restaurante;                                             //dos dois objetos passados como parâmetro. Também é possível ignorar determinado
		}                                                                   //parâmetro passando o nome do parâmetro como String.
		return null;
	}
	
	public Restaurante atualiza(Long idRestaurante, Restaurante restaurante) {	
		
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(idRestaurante);
		
		if (restauranteAtual.isPresent()) {
			restaurante.setId(restauranteAtual.get().getId());
			BeanUtils.copyProperties(restauranteAtual,restaurante);	
			restauranteRepository.save(restaurante);                        
			return restaurante;                                             
		}                                                                   
		return null;
	}
	
}
