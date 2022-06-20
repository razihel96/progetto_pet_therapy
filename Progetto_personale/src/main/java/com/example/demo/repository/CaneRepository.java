package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Cane;

public interface CaneRepository extends CrudRepository<Cane, Long> {
	
	//mi costruisco un metodo che mi dice se esiste un cane con quel microchip 
			public boolean existsByMicrochip(int microchip);

}
