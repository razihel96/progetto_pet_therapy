package com.example.demo.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cane;
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
	
	
}
