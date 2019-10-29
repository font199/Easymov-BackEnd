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
		
		return usuari;
	}
	
	public UsuariDto usuariToUsuariDto(Usuari usuari) {
		UsuariDto usuariDto = new UsuariDto();
		
		usuariDto.setId(usuari.getId());
		usuariDto.setNom(usuari.getNom());
		usuariDto.setIdGoogle(usuari.getIdGoogle());
		usuariDto.setMail(usuari.getMail());
		usuariDto.setFotoURL(usuari.getFotoURL());
		
		return usuariDto;
		
	}

}
