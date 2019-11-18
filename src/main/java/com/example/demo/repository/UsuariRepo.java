package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Usuari;

@Repository
public interface UsuariRepo extends JpaRepository <Usuari, Integer>{
	
	 Optional<Usuari> findByIdGoogle(String idGoogle);
	 
	 List<Usuari> findTop10ByOrderByPuntuacioDesc();

}
