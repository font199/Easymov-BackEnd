package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuari {

	@Id
	private Long idUsuari;
	
	private String nom;

	public Long getIdUsuari() {
		return idUsuari;
	}

	public void setIdUsuari(Long idUsuari) {
		this.idUsuari = idUsuari;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
