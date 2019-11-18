package com.example.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.Api;

@Api
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuariResDto implements Serializable {

	private static final long serialVersionUID = 6185001674605611857L;

	private int id;
	@NotNull
	private String nom;
	private String fotoURL;
	@NotNull
	private String idGoogle;
	@NotNull
	private String mail;
	private int puntuacio;
	
	private List<Integer> obstaclesIds = new ArrayList<>();

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getObstaclesIds() {
		return obstaclesIds;
	}

	public void setObstaclesIds(List<Integer> obstaclesIds) {
		this.obstaclesIds = obstaclesIds;
	}

	public int getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(int puntuacio) {
		this.puntuacio = puntuacio;
	}



}
