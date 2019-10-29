package com.example.demo.dto;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Obstacle;


@Component
public class ObstacleDtoConversor {
	
	public Obstacle obstacleDtoToObstacle(ObstacleDto obstacleDto) {
		Obstacle obstacle = new Obstacle();
		obstacle.setLatitud(obstacleDto.getLatitud());
		obstacle.setLongitud(obstacleDto.getLongitud());
		obstacle.setNom(obstacleDto.getNom());
		obstacle.setDescripcio(obstacleDto.getDescripcio());
	
		return obstacle;
	}
	
	public ObstacleDto obstacleToObstacleDto(Obstacle obstacle) {
		ObstacleDto obstacleDto = new ObstacleDto();
		
		obstacleDto.setId(obstacle.getId());
		obstacleDto.setLongitud(obstacle.getLongitud());
		obstacleDto.setLatitud(obstacle.getLatitud());
		obstacleDto.setNom(obstacle.getNom());
		obstacleDto.setDescripcio(obstacle.getDescripcio());
		
		return obstacleDto;
	}

}
