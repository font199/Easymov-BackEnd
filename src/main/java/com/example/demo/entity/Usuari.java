package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity 
public class Usuari {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@ApiModelProperty(hidden=true)
	private Long id;
	
	@NotNull
	private String nom;
	
	private String fotoURL;
	
	@NotNull
	private String idGoogle; 
	
	@NotNull
	private String mail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getFotoURL() {
		return fotoURL;
	}

	public void setFotoURL(String fotoURL) {
		this.fotoURL = fotoURL;
	}

	public String getIdGoogle() {
		return idGoogle;
	}

	public void setIdGoogle(String idGoogle) {
		this.idGoogle = idGoogle;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	
	
}

