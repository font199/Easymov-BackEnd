package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity 
public class Usuari {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@ApiModelProperty(hidden=true)
	private int id;
	
	@NotNull
	private String nom;
	
	private String fotoURL;
	
	@NotNull
	private String idGoogle; 
	
	@NotNull
	private String mail;
	
	@OneToMany(mappedBy = "idUsuariCreador")
	private List<Obstacle> obstacles;
	
	private int puntuacio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public List<Obstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(List<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}

	public int getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(int puntuacio) {
		this.puntuacio = puntuacio;
	}
	
	public void incrementarPuntuacio(int p) {
		this.puntuacio += p;
	}
	
	public void decrementarPuntuacio(int p)
	{
		this.puntuacio -= p;
	}

	
	
}

