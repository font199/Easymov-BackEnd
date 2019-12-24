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

import com.example.demo.dto.PuntuacioDto;
import com.example.demo.dto.TokenResDto;
import com.example.demo.dto.UsuariDto;
import com.example.demo.dto.UsuariRankingDto;
import com.example.demo.dto.UsuariResDto;
import com.example.demo.service.UsuariService;

@RestController
public class UsuariController {

	@Autowired
	private UsuariService usuariService;

	@GetMapping("/rest/usuaris")
	public List<UsuariDto> llistar() {
		return usuariService.llistar();
	}

	@GetMapping("/rest/usuari/{id}")
	public UsuariResDto buscar(@PathVariable int id) {
		return usuariService.buscar(id);
	}

	@PostMapping("/usuari")
	public TokenResDto insertar(@RequestBody UsuariDto usuariDto) {
		return usuariService.registrar(usuariDto);
	}

	@PutMapping("/rest/usuari/{id}")
	public int modificar(@PathVariable int id, @RequestBody UsuariDto usuariDto) {
		return usuariService.modificar(id, usuariDto);
	}

	@DeleteMapping("/rest/usuari/{id}")
	public void eliminar(@PathVariable int id) {
		usuariService.eliminar(id);
	}

	@PutMapping("/rest/puntuar/{id}")
	public int puntuarUsuari(@PathVariable int id, @RequestBody PuntuacioDto puntuacioDto) {
		return usuariService.puntuar(id, puntuacioDto);
	}

	@GetMapping("/rest/ranking")
	public List<UsuariRankingDto> ranking() {
		return usuariService.ranking();
	}

}
