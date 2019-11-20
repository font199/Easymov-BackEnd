package com.example.demo.dto;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Obstacle;
import com.example.demo.entity.Usuari;


@Component
public class ObstacleDtoConversor {
	
	public Obstacle obstacleDtoToObstacle(ObstacleDto obstacleDto) {
		Obstacle obstacle = new Obstacle();
		obstacle.setLatitud(obstacleDto.getLatitud());
		obstacle.setLongitud(obstacleDto.getLongitud());
		obstacle.setNom(obstacleDto.getNom());
		obstacle.setDescripcio(obstacleDto.getDescripcio());
	    obstacle.setIdUsuariCreador(obstacleDto.getIdUsuariCreador());
	    obstacle.setFotoUrl(obstacleDto.getFoto());
		return obstacle;
	}
	
	public ObstacleDto obstacleToObstacleDto(Obstacle obstacle) {
		ObstacleDto obstacleDto = new ObstacleDto();
		
		obstacleDto.setId(obstacle.getId());
		obstacleDto.setLongitud(obstacle.getLongitud());
		obstacleDto.setLatitud(obstacle.getLatitud());
		obstacleDto.setNom(obstacle.getNom());
		obstacleDto.setDescripcio(obstacle.getDescripcio());
		obstacleDto.setIdUsuariCreador(obstacle.getIdUsuariCreador());
		obstacleDto.setFoto(obstacle.getFotoUrl());
		
		return obstacleDto;
	}
	
	public ObstacleResDto obstacleToObstacleResDto(Obstacle obstacle) {
		ObstacleResDto obstacleResDto = new ObstacleResDto();
		
		obstacleResDto.setId(obstacle.getId());
		obstacleResDto.setLongitud(obstacle.getLongitud());
		obstacleResDto.setLatitud(obstacle.getLatitud());
		obstacleResDto.setNom(obstacle.getNom());
		obstacleResDto.setDescripcio(obstacle.getDescripcio());
		obstacleResDto.setIdUsuariCreador(obstacle.getIdUsuariCreador());
		obstacleResDto.getUsuarisLike().clear();
		
		if(obstacle.getUsuarisLike() != null) {
			for (Usuari u : obstacle.getUsuarisLike()) {
				obstacleResDto.getUsuarisLike().add(u.getId());		
			}
		}
		
		if(obstacle.getUsuarisDislike() != null) {
			for (Usuari u : obstacle.getUsuarisDislike()) {
				obstacleResDto.getUsuarisDisLike().add(u.getId());		
			}
		}
		
		if(obstacle.getUsuarisResolt() != null) {
			for (Usuari u : obstacle.getUsuarisResolt()) {
				obstacleResDto.getUsuarisResolt().add(u.getId());		
			}
		}
	
		return obstacleResDto;
	}

}
