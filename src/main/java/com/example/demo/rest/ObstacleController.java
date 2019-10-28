package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.ObstacleRepo;
import com.example.demo.entity.Obstacle;

@RestController
public class ObstacleController {

	@Autowired
	private ObstacleRepo repo;
	
	@GetMapping("/obstacles")
	public List<Obstacle> llistar() {
		return repo.findAll();
	}
	
	@GetMapping("/obstacle/{id}")
	public Optional<Obstacle> GetObstacle(@PathVariable int id) {
		return repo.findById(id);
	}

	@PostMapping("/obstacle")
	public int insertar(@RequestBody Obstacle obstacle) {
		Obstacle obstacleNou = repo.save(obstacle);
		return obstacleNou.getId();
	}

	@PutMapping("/obstacle")
	public int modificar(@RequestBody Obstacle obstacle) {
		Obstacle obstacleModificat = repo.save(obstacle);
		return obstacleModificat.getId();
	}

	@DeleteMapping("/obstacle/{id}")
	public void eliminar(@PathVariable int id) {
		repo.deleteById(id);
	}
}
