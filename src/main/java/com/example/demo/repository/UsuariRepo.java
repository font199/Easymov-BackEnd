package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Usuari;

@Repository
public interface UsuariRepo extends JpaRepository <Usuari, Long>{
	
	 Optional<Usuari> findByIdGoogle(String idGoogle);

}
