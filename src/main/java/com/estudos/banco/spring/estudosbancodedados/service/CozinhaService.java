package com.estudos.banco.spring.estudosbancodedados.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estudos.banco.spring.estudosbancodedados.domain.model.Cozinha;
import com.estudos.banco.spring.estudosbancodedados.repository.CozinhaRepository;


//Anotação @Service associada com classes que representam a ideia do padrão Service do Domain Driven Design. 
//Para ficar menos teórico pense em classes que representam algum fluxo de negócio da sua aplicação. 
//Por exemplo, um fluxo de finalização de compra envolve atualizar manipular o carrinho, enviar email, processar pagamento etc. 
//Este é o típico código que temos dificuldade de saber onde vamos colocar, em geral ele pode ficar num Service.
@Service
public class CozinhaService {
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	
	public List<Cozinha> listarCozinhas() {
		
		return  (List<Cozinha>) cozinhaRepository.findAll();
		
	}
	
	public Cozinha grava(Cozinha cozinha) {
		
	   return cozinhaRepository.save(cozinha);
	
	}
	
	public Optional<Cozinha> buscaId(Long codigo) {
		
		return  cozinhaRepository.findById(codigo);
	}
	
	public void deleta(Cozinha cozinha) {
		
		cozinhaRepository.delete(cozinha);
	}
	
	public void deleta(Long id) {
		cozinhaRepository.deleteById(id);
	}

	public Cozinha atualiza(Cozinha cozinha) {	
		
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinha.getId());
		
		if (cozinhaAtual.isPresent()) {
			BeanUtils.copyProperties(cozinha,cozinhaAtual, "id");	// A Classe abstrata BeanUtils que faz parte do pacote org.springframework.beans		
			cozinhaRepository.save(cozinha);                        //possui o método copyProperties, que substitui os valores das propriedades
			return cozinha;                                         //dos dois objetos passados como parâmetro. Também é possível ignorar determinado
		}                                                           //parâmetro passando o nome do parâmetro como String.
		return null;
	}
	
	public Cozinha atualiza(Long idCozinha, Cozinha cozinha) {	
		
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(idCozinha);
		
		if (cozinhaAtual.isPresent()) {
			cozinha.setId(cozinhaAtual.get().getId());
			BeanUtils.copyProperties(cozinhaAtual,cozinha);	
			cozinhaRepository.save(cozinha);                        
			return cozinha;                                             
		}                                                                   
		return null;
	}
	
	
	

}
