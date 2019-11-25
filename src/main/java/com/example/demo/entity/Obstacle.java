package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


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
	
	@ManyToMany()
	@JoinTable(name="Obstacles_usuarisLike",
    joinColumns=@JoinColumn(name="obstacle_id" ),
    inverseJoinColumns=@JoinColumn(name="usuari_id")) 
	private List<Usuari> usuarisLike; // llista dels usuaris que han fet like al obstacle.
	
	@ManyToMany()
	@JoinTable(name="Obstacles_usuarisDislike",
    joinColumns=@JoinColumn(name="obstacle_id" ),
    inverseJoinColumns=@JoinColumn(name="usuari_id")) 
	private List<Usuari> usuarisDislike; // llista dels usuaris que hanfet dislike al obstacle. //max 5
	
	@ManyToMany()
	@JoinTable(name="Obstacles_usuarisResolt",
    joinColumns=@JoinColumn(name="obstacle_id" ),
    inverseJoinColumns=@JoinColumn(name="usuari_id")) 
	private List<Usuari> usuarisResolt;  // llista dels usuaris que consideren l'obstacle resolt.
	
	
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

	public List<Usuari> getUsuarisLike() {
		return usuarisLike;
	}

	public void setUsuarisLike(List<Usuari> usuarisLike) {
		this.usuarisLike = usuarisLike;
	}

	public List<Usuari> getUsuarisDislike() {
		return usuarisDislike;
	}

	public void setUsuarisDislike(List<Usuari> usuarisDislike) {
		this.usuarisDislike = usuarisDislike;
	}

	public List<Usuari> getUsuarisResolt() {
		return usuarisResolt;
	}

	public void setUsuarisResolt(List<Usuari> usuarisResolt) {
		this.usuarisResolt = usuarisResolt;
	}

	public boolean checkNumberDislike() {
		int likes = this.usuarisLike.size();
		int dislikes = this.usuarisDislike.size();
		if(likes - dislikes < -2)return true;
		return false;
	}




	
}
