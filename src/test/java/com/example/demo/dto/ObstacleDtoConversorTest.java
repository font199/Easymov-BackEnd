package com.example.demo.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Obstacle;

public class ObstacleDtoConversorTest {

	@Autowired 
	ObstacleDtoConversor conversor;
	
	ObstacleDto obsDto;
	Obstacle obs;
	
	@Before
	public void beforeEachTest (){
		conversor = new ObstacleDtoConversor();
		
		obsDto = new ObstacleDto();
		obsDto.setId(1);
		obsDto.setNom("Escales");
		obsDto.setDescripcio("Moltes Escales");
		obsDto.setLatitud(123.23);
		obsDto.setLongitud(345.567);
		obsDto.setFoto("www.foto.com");
		obsDto.setIdUsuariCreador(101);
		
		obs = new Obstacle();
		obs.setId(1);
		obs.setNom("Escales");
		obs.setDescripcio("Moltes Escales");
		obs.setLatitud(123.23);
		obs.setLongitud(345.567);
		obs.setFotoUrl("www.foto.com");
		obs.setIdUsuariCreador(101);
		
		
	}
	
	@Test
	public void obstacleDtotoObstacleTest() {
		Obstacle o = conversor.obstacleDtoToObstacle(obsDto);
		assertEquals(0, o.getId());
		assertEquals("Escales", o.getNom());
		assertEquals("Moltes Escales", o.getDescripcio());
		assertEquals(123.23, o.getLatitud(),0.1);
		assertEquals(345.567, o.getLongitud(),0.1);
		assertEquals("www.foto.com", o.getFotoUrl());
		assertEquals(101, o.getIdUsuariCreador());
	}
	
	@Test
	public void obstacletoObstacleDtoTest() {
		ObstacleDto oDto = conversor.obstacleToObstacleDto(obs);
		assertEquals(1, obsDto.getId());
		assertEquals("Escales", obsDto.getNom());
		assertEquals("Moltes Escales", obsDto.getDescripcio());
		assertEquals(123.23, obsDto.getLatitud(),0.1);
		assertEquals(345.567, obsDto.getLongitud(),0.1);
		assertEquals("www.foto.com", obsDto.getFoto());
		assertEquals(101, obsDto.getIdUsuariCreador());
	}
	
	@Test
	public void obstacleToObstacleResDto() {
		ObstacleResDto resoDto = conversor.obstacleToObstacleResDto(obs);
		assertEquals(1, resoDto.getId());
		assertEquals("Escales", resoDto.getNom());
		assertEquals("Moltes Escales", resoDto.getDescripcio());
		assertEquals(123.23, resoDto.getLatitud(),0.1);
		assertEquals(345.567, resoDto.getLongitud(),0.1);
		assertEquals("www.foto.com", resoDto.getFoto());
		assertEquals(101, resoDto.getIdUsuariCreador());
		
	}

}
