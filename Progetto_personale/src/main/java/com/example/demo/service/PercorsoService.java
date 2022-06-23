package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Percorso;
import com.example.demo.repository.PercorsoRepository;

@Service
public class PercorsoService {

	private PercorsoRepository percorsoRepository;
	
	
	public void save(Percorso percorso) {
		percorsoRepository.save(percorso);
	}
}
