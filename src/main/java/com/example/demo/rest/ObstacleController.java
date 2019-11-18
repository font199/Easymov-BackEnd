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
import com.example.demo.dto.ObstacleResDto;
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
	public ObstacleResDto GetObstacle(@PathVariable int id) {
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
	
	@PutMapping("/obstacle/{idObstacle}/like/{idUsuari}")
	public void like(@PathVariable int idObstacle, @PathVariable int idUsuari ) {
		 obstacleService.likeObstacle(idObstacle, idUsuari);
	}
	
	@PutMapping("/obstacle/{idObstacle}/treurelike/{idUsuari}")
	public void treureLike(@PathVariable int idObstacle, @PathVariable int idUsuari ) {
		 obstacleService.treureLikeObstacle(idObstacle, idUsuari);
	}
	
	@PutMapping("/obstacle/{idObstacle}/dislike/{idUsuari}")
	public void dislike(@PathVariable int idObstacle, @PathVariable int idUsuari) {
		 obstacleService.dislikeObstacle(idObstacle, idUsuari);
	}
	
	@PutMapping("/obstacle/{idObstacle}/treuredislike/{idUsuari}")
	public void treureDislike(@PathVariable int idObstacle, @PathVariable int idUsuari) {
		 obstacleService.treureDislikeObstacle(idObstacle, idUsuari);
	}


}
