package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Cane {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //chiave primaria nel mapping
	
	@NotBlank
	private int microchip;
	
	@NotBlank
	private String nome;

	@NotBlank
	private String razza;
	
	@NotBlank
	private String curriculum;
	
	@NotBlank
	private String photos;
	
	
	
	
	//un operatore addestrano più cani
	@ManyToOne
	private Operatore operatore;
	
	
	//più cani possono intraprendere lo stesso percorso
	@ManyToOne
	private Percorso percorso;
	
	


	/* SETTER & GETTER */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getMicrochip() {
		return microchip;
	}

	public void setMicrochip(int microchip) {
		this.microchip = microchip;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRazza() {
		return razza;
	}

	public void setRazza(String razza) {
		this.razza = razza;
	}

	public String getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}
	
	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public Operatore getOperatore() {
		return operatore;
	}

	public void setOperatore(Operatore operatore) {
		this.operatore = operatore;
	}
	

	
}
