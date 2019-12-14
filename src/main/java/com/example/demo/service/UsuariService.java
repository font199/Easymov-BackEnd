package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.PuntuacioDto;
import com.example.demo.dto.UsuariDto;
import com.example.demo.dto.UsuariDtoConversor;
import com.example.demo.dto.UsuariRankingDto;
import com.example.demo.dto.UsuariResDto;
import com.example.demo.entity.Obstacle;
import com.example.demo.entity.Usuari;
import com.example.demo.exceptions.TaskManagerBussinessException;
import com.example.demo.exceptions.codes.ExceptionsCodes;
import com.example.demo.repository.UsuariRepo;

@Service
public class UsuariService {

    @Autowired
    private UsuariRepo usuariRepo;
    
    @Autowired
    private UsuariDtoConversor usuariDtoConversor;
	
    @Transactional
	public int registrar(UsuariDto usuariDto) {
		
		 Optional<Usuari> usuariExistent =  usuariRepo.findByIdGoogle(usuariDto.getIdGoogle());
		 Usuari usuari = usuariDtoConversor.usuariDtoToUsuari(usuariDto);
		
	 	if(usuariExistent.isPresent()) { // si l'usuari existeix, retornem la seva id
    		return usuariExistent.get().getId();
    	} else {
    		//inicialitzem la puntuacio a 0
    		usuari.setPuntuacio(0);
    		// si l'usuari no existeix el crearem i retornem la seva id
    		Usuari usuariCreat = usuariRepo.save(usuari);
    		return usuariCreat.getId();
    	}
	}
	
	@Transactional
	public int modificar(int id, UsuariDto usuariDto) {
		
		Optional<Usuari> usuariExistent =  usuariRepo.findById(id);
		
		if(usuariExistent.isPresent()) {
			Usuari usuari = usuariDtoConversor.usuariDtoToUsuari(usuariDto);
			usuari.setId(id);
			
			usuariRepo.save(usuari);
			return id;
		}else {
			throw new TaskManagerBussinessException(ExceptionsCodes.USE_NOT_FOUND, HttpStatus.NOT_FOUND,
					"usuari_no_existeix");
		}
		 
	}
	
	public List<UsuariDto> llistar(){
		List<UsuariDto>  listUsuarisDto = new ArrayList<UsuariDto>();
		usuariRepo.findAll().stream().map(usuariDtoConversor::usuariToUsuariDto).forEach(listUsuarisDto ::add);
		return listUsuarisDto;
	}
	
	public UsuariResDto buscar(int id) {
		UsuariResDto usuariResDto = new UsuariResDto();
		Usuari usuari = usuariRepo.getOne(id);
		try {
			usuariResDto = usuariDtoConversor.usuariToUsuariResDto(usuari);
		} catch (EntityNotFoundException ex) {
			throw new TaskManagerBussinessException(ExceptionsCodes.USE_NOT_FOUND, HttpStatus.NOT_FOUND,
					"usuari_id_" + id + "_not_found");
		}
		return usuariResDto;
	}
 
	public void eliminar(int id) {
		Optional<Usuari> usuariExistent =  usuariRepo.findById(id);
		if(usuariExistent.isPresent()) {
			//Si te obstacles els asignarem a l'usuari del Sistema
			List<Obstacle> obstacles = usuariExistent.get().getObstacles();
			if(obstacles != null) {
				
				//Mirem si existeix l'usuari System i sino el crearem
				Optional<Usuari> systemExistent =  usuariRepo.findById(1000);
				if(!systemExistent.isPresent()) {
					Usuari system = new Usuari();
					system.setIdGoogle("ddddd");
					system.setNom("System");
					system.setId(1000);
					system.setMail("bbbb");
					Usuari userCreat = usuariRepo.save(system);
					userCreat.setId(1000);
				}
				//Recorrem els obstacles de l'usuari a borrar i l'hi asginem el user System
				obstacles.forEach((obs) -> {
					obs.setIdUsuariCreador(1000); 
				});
			}
			usuariExistent.get().setObstacles(null);
			usuariRepo.deleteById(id); 
		} else {
			throw new TaskManagerBussinessException(ExceptionsCodes.USE_EMPTY_RESULT, HttpStatus.NOT_FOUND,
					"usuari_id_" + id + "_empty_result");
		}
	}

	public int puntuar(int id, PuntuacioDto puntuacioDto) {
		Optional<Usuari> usuariExistent =  usuariRepo.findById(id);
		if(usuariExistent.isPresent()) {
			Usuari usuari = usuariExistent.get();
			usuari.setPuntuacio(puntuacioDto.getPuntuacio());
			usuariRepo.save(usuari);
			return usuari.getId();
		}else {
			throw new TaskManagerBussinessException(ExceptionsCodes.USE_NOT_FOUND, HttpStatus.NOT_FOUND,"usuari_no_existeix");
		}	
	}
	
	
	public List<UsuariRankingDto> ranking(){
		List<UsuariRankingDto>  listUsuariRankingDto = new ArrayList<UsuariRankingDto>();
		usuariRepo.findTop10ByOrderByPuntuacioDesc().stream().map(usuariDtoConversor::usuariToUsuariRankingDto).forEach(listUsuariRankingDto ::add);
		return listUsuariRankingDto;
	}

}
