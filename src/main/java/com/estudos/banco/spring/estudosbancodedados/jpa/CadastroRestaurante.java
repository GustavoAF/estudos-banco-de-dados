package com.estudos.banco.spring.estudosbancodedados.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import com.estudos.banco.spring.estudosbancodedados.domain.model.Restaurante;

@Component
public class CadastroRestaurante {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Restaurante> listar(){
		
		TypedQuery<Restaurante> query = entityManager.createQuery(" from Restaurante", Restaurante.class);
		
		return query.getResultList();
	}

}
