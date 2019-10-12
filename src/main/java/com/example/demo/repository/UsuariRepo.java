package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Usuari;

@Repository
public interface UsuariRepo extends JpaRepository <Usuari, Long>{

}
