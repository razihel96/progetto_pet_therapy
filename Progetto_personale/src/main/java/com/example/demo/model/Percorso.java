package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Percorso {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //chiave primaria nel mapping
	
	@NotBlank
	private String nome;

	@NotBlank
	private String descrizione;

	
	
	//percorso intrapreso da più cani
	@OneToMany(mappedBy="operatore")
	private List<Cane> cani;
	
	
	//un percorso può essere intrapreso da più utenti
	@OneToMany(mappedBy="percorso")
	private List<User> users;
	
	
	
	
	/* SETTER & GETTERS */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	

}
