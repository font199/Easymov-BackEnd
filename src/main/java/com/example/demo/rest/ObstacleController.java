package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ObstacleDto;
import com.example.demo.service.ObstacleService;

@RestController
public class ObstacleController {

	@Autowired
	private ObstacleService obstacleService;
	
	@GetMapping("/obstacles")
	public List<ObstacleDto> llistar() {
		return obstacleService.llistar();
	}
	
	@GetMapping("/obstacle/{id}")
	public ObstacleDto GetObstacle(@PathVariable int id) {
		return obstacleService.getObstacle(id);
	}

	@PostMapping("/obstacle")
	public int insertar(@RequestBody ObstacleDto obstacleDto) {
		return obstacleService.insertar(obstacleDto);
	}

	@PutMapping("/obstacle/{id}")
	public int modificar(@PathVariable int id, @RequestBody ObstacleDto obstacleDto) {
		return obstacleService.modificar(id, obstacleDto);
	}

	@DeleteMapping("/obstacle/{id}")
	public void eliminar(@PathVariable int id) {
		 obstacleService.eliminar(id);
	}
	
	// Mirar si el 5e bot negatiu no te mes de 5 bots positius, llavors eliminem el obstacle.
	// Poder fer un push de el nombre de negatius que cal tenir per eliminar el obstacle
}
