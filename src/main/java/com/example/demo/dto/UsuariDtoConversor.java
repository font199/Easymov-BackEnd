package com.example.demo.dto;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Usuari;


@Component
public class UsuariDtoConversor {
	
	public Usuari usuariDtoToUsuari(UsuariDto usuariDto) {
		Usuari usuari = new Usuari();
		
		usuari.setIdGoogle(usuariDto.getIdGoogle());
		usuari.setNom(usuariDto.getNom());
		usuari.setMail(usuariDto.getMail());
		usuari.setFotoURL(usuariDto.getFotoURL());
		usuari.setPuntuacio(usuariDto.getPuntuacio());
		
		return usuari;
	}
	
	public UsuariDto usuariToUsuariDto(Usuari usuari) {
		UsuariDto usuariDto = new UsuariDto();
		
		usuariDto.setId(usuari.getId());
		usuariDto.setNom(usuari.getNom());
		usuariDto.setIdGoogle(usuari.getIdGoogle());
		usuariDto.setMail(usuari.getMail());
		usuariDto.setFotoURL(usuari.getFotoURL());
		usuariDto.setPuntuacio(usuari.getPuntuacio());
		
		// Todo: retornar llistat de obstacles nomes per la crida del getUsuari i no de getUsuaris
		return usuariDto;
		
	}

}
