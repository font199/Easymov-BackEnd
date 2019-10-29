package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ObstacleDto;
import com.example.demo.dto.ObstacleDtoConversor;
import com.example.demo.entity.Obstacle;
import com.example.demo.exceptions.TaskManagerBussinessException;
import com.example.demo.exceptions.codes.ExceptionsCodes;
import com.example.demo.repository.ObstacleRepo;


@Service
public class ObstacleService {

	@Autowired
	private ObstacleRepo obstacleRepo;
	
	@Autowired 
	private ObstacleDtoConversor obstacleDtoConversor;
	
	public List<ObstacleDto> llistar() {
		List<ObstacleDto>  listObstacleDto = new ArrayList<ObstacleDto>();
		obstacleRepo.findAll().stream().map(obstacleDtoConversor::obstacleToObstacleDto).forEach(listObstacleDto ::add);
		return listObstacleDto;
	}

	public ObstacleDto getObstacle(int id) {
		ObstacleDto obstacleDto = new ObstacleDto();
		Obstacle obstacle = obstacleRepo.getOne(id);
		try {
			obstacleDto = obstacleDtoConversor.obstacleToObstacleDto(obstacle);
		} catch (EntityNotFoundException ex) {
			throw new TaskManagerBussinessException(ExceptionsCodes.OBS_NOT_FOUND, HttpStatus.NOT_FOUND,
					"obstacle_id_" + id + "_not_found");
		}
		return obstacleDto;
	}

	public int insertar(ObstacleDto obstacleDto) {;
		Optional<Obstacle> obstacleExistent =  obstacleRepo.findByLongitudAndLatitud(obstacleDto.getLongitud(), obstacleDto.getLatitud());
		Obstacle obtacle = obstacleDtoConversor.obstacleDtoToObstacle(obstacleDto);
		
	 	if(!obstacleExistent.isPresent()) { 
    		Obstacle obstacleCreat = obstacleRepo.save(obtacle);
    		return obstacleCreat.getId();
    	} else { 
    		throw new TaskManagerBussinessException(ExceptionsCodes.OBS_DUPLICATE, HttpStatus.CONFLICT,
					"longitud_i_latitud_ja_existents");
    	}
	}

	public int modificar(int id, ObstacleDto obstacleDto) {
		Optional<Obstacle> obstacleExistent = obstacleRepo.findById(id);
		
		if(obstacleExistent.isPresent()) {
			Obstacle obstacle = obstacleDtoConversor.obstacleDtoToObstacle(obstacleDto);
			obstacle.setId(id);
			
			obstacleRepo.save(obstacle);
			return id;
		}else {
			throw new TaskManagerBussinessException(ExceptionsCodes.OBS_NOT_FOUND, HttpStatus.NOT_FOUND,
					"obstacle_no_existeix");
		}
	}

	public void eliminar(int id) {
		Optional<Obstacle> obstacleExistent = obstacleRepo.findById(id);
		if(obstacleExistent.isPresent()) {
			obstacleRepo.deleteById(id);
		} else {
			throw new TaskManagerBussinessException(ExceptionsCodes.OBS_EMPTY_RESULT, HttpStatus.NOT_FOUND,
					"obstacle_id_" + id + "_empty_result");
		}
		
	}
}
