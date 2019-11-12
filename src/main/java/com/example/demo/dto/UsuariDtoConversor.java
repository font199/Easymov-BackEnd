package com.example.demo.dto;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Obstacle;
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
		
		return usuariDto;
		
	}
	
	public UsuariResDto usuariToUsuariDto2(Usuari usuari) {
		UsuariResDto usuariResDto = new UsuariResDto();
		
		usuariResDto.setId(usuari.getId());
		usuariResDto.setNom(usuari.getNom());
		usuariResDto.setIdGoogle(usuari.getIdGoogle());
		usuariResDto.setMail(usuari.getMail());
		usuariResDto.setFotoURL(usuari.getFotoURL());
		usuariResDto.getObstaclesIds().clear(); 
		if(usuari.getObstacles() != null) {
			for (Obstacle o : usuari.getObstacles()) {
				usuariResDto.getObstaclesIds().add(o.getId());		
			}
		}
		return usuariResDto;
		
	}

}
