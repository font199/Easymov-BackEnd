package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Obstacle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String fotoUrl;
	private double longitud;
	private double latitud;
	private String descripcio;
	private int idUsuariCreador;
	
////	@JoinColumn(name = "fk_usuari", nullable = false)
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//	private Usuari usuariObst;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIdUsuariCreador() {
		return idUsuariCreador;
	}

	public void setIdUsuariCreador(int idUsuariCreador) {
		this.idUsuariCreador = idUsuariCreador;
	}


	
}
