package com.example.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.Api;

@Api
@JsonIgnoreProperties(ignoreUnknown = true)
public class PuntuacioDto implements Serializable {

	private static final long serialVersionUID = 6185001674605611857L;

	
	private int id;
	
	private int puntuacio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(int puntuacio) {
		this.puntuacio = puntuacio;
	}
}