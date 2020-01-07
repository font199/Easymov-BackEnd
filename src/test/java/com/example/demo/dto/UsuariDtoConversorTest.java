package com.example.demo.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Usuari;

public class UsuariDtoConversorTest {

	@Autowired 
	UsuariDtoConversor conversor;
	
	UsuariDto uDto;
	Usuari usuari;
	
	@Before
	public void beforeEachTest (){
		conversor = new UsuariDtoConversor();
		
		uDto = new UsuariDto();
		//no importa id pq el primer sempre li posa un 0 i al segon un 1
		uDto.setId(1);
		uDto.setNom("test");
		uDto.setFotoURL("foto");
		uDto.setIdGoogle("googleId");
		uDto.setMail("email");
		uDto.setPuntuacio(10);
		
		usuari = new Usuari();
		usuari.setId(1);
		usuari.setNom("test");
		usuari.setFotoURL("foto");
		usuari.setIdGoogle("googleId");
		usuari.setMail("email");
		usuari.setPuntuacio(10);
		
	
	}
	
	@Test
	public void usuariDtoToUsuari() {
		
		Usuari u = conversor.usuariDtoToUsuari(uDto);
		assertEquals(0, u.getId());
		assertEquals("foto", u.getFotoURL());
		assertEquals("googleId", u.getIdGoogle());
		assertEquals("email", u.getMail());
		assertEquals("test", u.getNom());
		assertEquals(10, u.getPuntuacio());
		
	}
	
	@Test
	public void usuariToUsuariDtoTest() {
		
		UsuariDto u = conversor.usuariToUsuariDto(usuari);
		assertEquals(1, u.getId());
		assertEquals("foto", u.getFotoURL());
		assertEquals("googleId", u.getIdGoogle());
		assertEquals("email", u.getMail());
		assertEquals("test", u.getNom());
		assertEquals(10, u.getPuntuacio());
		
	}
	
	@Test
	public void usuariToUsuariResDtoTest() {
		
		UsuariResDto u = conversor.usuariToUsuariResDto(usuari);
		assertEquals(1, u.getId());
		assertEquals("foto", u.getFotoURL());
		assertEquals("googleId", u.getIdGoogle());
		assertEquals("email", u.getMail());
		assertEquals("test", u.getNom());
		assertEquals(10, u.getPuntuacio());
		
	}
	
	@Test
	public void usuariToUsuariRankingDto() {
		
		UsuariRankingDto ranking = conversor.usuariToUsuariRankingDto(usuari);
		assertEquals(1, ranking.getId());
		assertEquals("test", ranking.getNom());
		assertEquals(10, ranking.getPuntuacio());
		
		
	}
	

}
