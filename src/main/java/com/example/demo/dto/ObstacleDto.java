package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.Api;

@Api
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObstacleDto implements Serializable {

	private static final long serialVersionUID = 6185001674605611857L;

	private int id;
	@NotNull
	private String nom;
	private String foto;
	private double longitud;
	private double latitud;
	private String descripcio;
	@NotNull
	private int idUsuariCreador;

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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public int getIdUsuariCreador() {
		return idUsuariCreador;
	}

	public void setIdUsuariCreador(int idUsuariCreador) {
		this.idUsuariCreador = idUsuariCreador;
	}
	
	

}
