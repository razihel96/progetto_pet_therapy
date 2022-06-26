package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Cane;
import com.example.demo.model.Operatore;

public interface CaneRepository extends CrudRepository<Cane, Long> {
	
	//mi costruisco un metodo che mi dice se esiste un cane con quel microchip 
			public boolean existsByNome(String nome);

			public List<Cane> findByOperatore(Operatore operatore);

}
