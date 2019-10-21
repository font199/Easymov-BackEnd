package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuari;
import com.example.demo.repository.UsuariRepo;

@Service
public class UsuariService {

    @Autowired
    private UsuariRepo usuariRepo;
	
	public Long registrar(Usuari usuari) {
		
		 Optional<Usuari> ussuariExistent =  usuariRepo.findByIdGoogle(usuari.getIdGoogle());
		 
	 	if(ussuariExistent.isPresent()) {
    		return ussuariExistent.get().getId();
    	} else {
    		Usuari usuariCreat = usuariRepo.save(usuari);
    		return usuariCreat.getId();
    	}
		
	}
}
