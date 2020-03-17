package com.estudos.banco.spring.estudosbancodedados.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.estudos.banco.spring.estudosbancodedados.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CrudRepository<Restaurante, Long>{

}
