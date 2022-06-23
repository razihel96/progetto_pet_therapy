package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cane;
import com.example.demo.model.Operatore;
import com.example.demo.repository.CaneRepository;


@Service
public class CaneService {

	//repository
	@Autowired
	private CaneRepository caneRepository;

	public void save(Cane cane) {
		caneRepository.save(cane);
	}
	
	public void delete(Cane cane) {
		caneRepository.delete(cane);
	}
	
	public boolean alreadyExists(Cane cane) {
		return caneRepository.existsByMicrochip(cane.getMicrochip());
	}

	//IMMAGINI
	public Cane inserisci(Cane cane) {
		return caneRepository.save(cane);
	}

	public Cane findById(Long id) {
		return caneRepository.findById(id).get();
	}

	
	
	//mi ritorna la lista di tutti i cani
	public List<Cane> findAll() {
		List<Cane> elencoCani = new ArrayList<>();
		for(Cane c : caneRepository.findAll()) {
			elencoCani.add(c);
		}
		
		return elencoCani;
	}
	
	
	
	//mi ritorna la lista dei cani di un operatore specifico
	public List<Cane> getByOperatore(Operatore operatore) {
		List<Cane> cani2operatore = new ArrayList<>();
		List<Cane> c = caneRepository.findByOperatore(operatore);
		for(Cane cane : c) {
			cani2operatore.add(cane);
		}
		
		return cani2operatore;
	}

	public void deleteById(Long id) {
		caneRepository.deleteById(id);
	}
	
	
	
	
	
	
	
}
