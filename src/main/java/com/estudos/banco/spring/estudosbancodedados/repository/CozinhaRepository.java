package com.estudos.banco.spring.estudosbancodedados.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.estudos.banco.spring.estudosbancodedados.domain.model.Cozinha;


@Repository //Anotação utilizada para a camada de persistência. Normalmente anotamos classes que representam um DAO, Repositório etc
public interface CozinhaRepository extends CrudRepository<Cozinha,Long >{

}
