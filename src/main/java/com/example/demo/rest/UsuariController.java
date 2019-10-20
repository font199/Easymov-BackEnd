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

import com.example.demo.entity.Usuari;
import com.example.demo.repository.UsuariRepo;

@RestController
public class UsuariController {

	@Autowired
	private UsuariRepo repo;

	@GetMapping("/usuaris")
	public List<Usuari> llistar() {
		return repo.findAll();
	}

	@PostMapping("/usuari")
	public Long insertar(@RequestBody Usuari usuari) {
		Usuari usuariCreat = repo.save(usuari);
		return usuariCreat.getId();
	}

	@PutMapping("/usuari")
	public Long modificar(@RequestBody Usuari usuari) {
		Usuari usuariModificat = repo.save(usuari);
		return usuariModificat.getId();
	}

	@DeleteMapping("/usuari/{id}")
	public void eliminar(@PathVariable Long id) {
		repo.deleteById(id);
	}

}
