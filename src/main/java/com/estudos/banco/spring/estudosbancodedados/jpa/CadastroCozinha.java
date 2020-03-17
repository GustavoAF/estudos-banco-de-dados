package com.estudos.banco.spring.estudosbancodedados.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.estudos.banco.spring.estudosbancodedados.domain.model.Cozinha;

@Component
public class CadastroCozinha {

	//Esta anotação funciona como um Container que guarda as entidades que estão 
	//sendo gerenciadas pelo EntityManager, ele funciona como o CACHE L1 do EntityManager
	@PersistenceContext                   
	private EntityManager entityManager;
	
	public List<Cozinha> listar(){
		
	 TypedQuery<Cozinha> query = entityManager.createQuery("from Cozinha", Cozinha.class);
	 
	 return query.getResultList();
	}
}
