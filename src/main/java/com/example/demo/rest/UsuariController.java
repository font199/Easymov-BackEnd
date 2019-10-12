package com.example.demo.rest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Usuari;
import com.example.demo.repository.UsuariRepo; 



@RestController
@RequestMapping("/usuari")
public class UsuariController {
	
	@Autowired
	private UsuariRepo repo;

	@GetMapping
	public List<Usuari> llistar(){
		return repo.findAll();
	}
	
	@PostMapping
	public void insertar (@RequestBody Usuari usuari) {
		repo.save(usuari);
	}
	
	@PutMapping
	public void modificar (@RequestBody Usuari usuari) {
		repo.save(usuari);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		repo.deleteById(id);
	}
	
	
//	@RequestMapping(value = "/usuari", method = RequestMethod.GET)
//    public List<Usuari> getEmployees() {
//		return repo.findAll();
//	}
	
//	@GetMapping("/person/{id}")
//	  public @ResponseBody ResponseEntity<String> getPersonById(@PathVariable String id){
//	    return new ResponseEntity<String>("Response from GET with id " +id,HttpStatus.OK); 
//	


}
