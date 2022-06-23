package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Operatore;

public interface OperatoreRepository extends CrudRepository<Operatore, Long> { //Long = tipo dell'identificatore (nel nostro caso)
	
	//mi costruisco un metodo che mi dice se esiste una persona con quel nome e cognome
	public boolean existsByNomeAndCognome(String nome, String cognome);
	
		
	
}

