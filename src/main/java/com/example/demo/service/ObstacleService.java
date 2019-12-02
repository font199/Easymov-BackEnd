package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ObstacleDto;
import com.example.demo.dto.ObstacleDtoConversor;
import com.example.demo.dto.ObstacleResDto;
import com.example.demo.entity.Obstacle;
import com.example.demo.entity.Usuari;
import com.example.demo.exceptions.TaskManagerBussinessException;
import com.example.demo.exceptions.codes.ExceptionsCodes;
import com.example.demo.repository.ObstacleRepo;
import com.example.demo.repository.UsuariRepo;


@Service
public class ObstacleService {

	@Autowired
	private ObstacleRepo obstacleRepo;
	
	@Autowired
	private UsuariRepo usuariRepo;
	
	@Autowired 
	private ObstacleDtoConversor obstacleDtoConversor;
	
	public List<ObstacleDto> llistar() {
		List<ObstacleDto>  listObstacleDto = new ArrayList<ObstacleDto>();
		obstacleRepo.findAll().stream().map(obstacleDtoConversor::obstacleToObstacleDto).forEach(listObstacleDto ::add);
		return listObstacleDto;
	}

	public ObstacleResDto getObstacle(int id) {
		ObstacleResDto obstacleResDto = new ObstacleResDto();
		Obstacle obstacle = obstacleRepo.getOne(id);
		try {
			obstacleResDto = obstacleDtoConversor.obstacleToObstacleResDto(obstacle);
		} catch (EntityNotFoundException ex) {
			throw new TaskManagerBussinessException(ExceptionsCodes.USE_EMPTY_RESULT, HttpStatus.NOT_FOUND,
					"usuari_id_" + id + "_empty_result");
		}
		return obstacleResDto;
	}

	public int insertar(ObstacleDto obstacleDto) {;
		Optional<Obstacle> obstacleExistent =  obstacleRepo.findByLongitudAndLatitud(obstacleDto.getLongitud(), obstacleDto.getLatitud());
		Obstacle obtacle = obstacleDtoConversor.obstacleDtoToObstacle(obstacleDto);
		
	 	if(!obstacleExistent.isPresent()) {
	 		
	 		Usuari u = usuariRepo.findById(obstacleDto.getIdUsuariCreador())
	 		        .orElseThrow(() -> new TaskManagerBussinessException(ExceptionsCodes.USE_EMPTY_RESULT, HttpStatus.FOUND,
							"Usuari no existent"));
	 			u.incrementarPuntuacio(10);//augmentem 10 punts per crear un obstacle
		 		obstacleRepo.save(obtacle);
		 		Obstacle obstacleCreat = obstacleRepo.save(obtacle);
	    		return obstacleCreat.getId();
    		
    	} else { 
    		throw new TaskManagerBussinessException(ExceptionsCodes.OBS_DUPLICATE, HttpStatus.CONFLICT,
					"longitud_i_latitud_ja_existents");
    	}
	}

	public int modificar(int id, ObstacleDto obstacleDto) {
		Optional<Obstacle> obstacleExistent = obstacleRepo.findById(id);
		
		if(obstacleExistent.isPresent()) {
			Obstacle obstacle = obstacleDtoConversor.obstacleDtoToObstacle(obstacleDto);
			obstacle.setId(id);
			
			obstacleRepo.save(obstacle);
			return id;
		}else {
			throw new TaskManagerBussinessException(ExceptionsCodes.OBS_NOT_FOUND, HttpStatus.NOT_FOUND,
					"obstacle_no_existeix");
		}
	}

	public void eliminar(int id) {
		Optional<Obstacle> obstacleExistent = obstacleRepo.findById(id);
		if(obstacleExistent.isPresent()) {
			obstacleRepo.deleteById(id);
		} else {
			throw new TaskManagerBussinessException(ExceptionsCodes.OBS_EMPTY_RESULT, HttpStatus.NOT_FOUND,
					"obstacle_id_" + id + "_empty_result");
		}
		
	}

	public void likeObstacle(int idObstacle, int idUsuariVotador) {
		this.votar(idObstacle, idUsuariVotador, true);
		
	}

	public void dislikeObstacle(int idObstacle, int idUsuariVotador) {
		this.votar(idObstacle, idUsuariVotador, false);
		
	}
	
	public void votar(int idObstacle, int idUsuariVotador, boolean esLike) {
		Optional<Obstacle> obstacleExistent =  obstacleRepo.findById(idObstacle);
		Optional<Usuari> usuariExistent =  usuariRepo.findById(idUsuariVotador);
		
		if(obstacleExistent.isPresent()) {
			Obstacle obstacle = obstacleExistent.get();
			if(usuariExistent.isPresent()) {
				Usuari usuariVotador = usuariExistent.get();
				
				if(esLike) { // Like a Objecte
					if(obstacle.getUsuarisLike().indexOf(usuariVotador) == -1) { // si l'usuari no ha votat like anteriorment
						 //Afegim usuari que ha fet like a la llista de l'obstacle
						obstacle.getUsuarisLike().add(usuariVotador);
						
						//treiem vot de dislike si en te
						if(obstacle.getUsuarisDislike().indexOf(usuariVotador) != -1) {
							obstacle.getUsuarisDislike().remove(obstacle.getUsuarisDislike().indexOf(usuariVotador));
						}
						//Donem puntuació a l'usuari creador de l'obstacle
						Optional<Usuari> usuariCreadorE = usuariRepo.findById(obstacle.getIdUsuariCreador());
						if(usuariCreadorE.isPresent()) {
							Usuari usuariCreador = usuariCreadorE.get(); 
							usuariCreador.incrementarPuntuacio(1); // Incrementem 1 PUNT
							usuariRepo.save(usuariCreador);
						}
					}else {
						throw new TaskManagerBussinessException(ExceptionsCodes.OBS_ALREADY_VOTED, HttpStatus.NOT_FOUND,"usuari ja ha votat");
					}
				}else { // Dislike a Objecte
					if(obstacle.getUsuarisDislike().indexOf(usuariVotador) == -1) { // si l'usuari no ha votat dislike anteriorment 
						//Afegim usuari que ha fet like a la llista de l'obstacle
						obstacle.getUsuarisDislike().add(usuariVotador);
						
						//treiem vot de like si en te
						if(obstacle.getUsuarisLike().indexOf(usuariVotador) != -1) {
							obstacle.getUsuarisLike().remove(obstacle.getUsuarisLike().indexOf(usuariVotador));
						}
						if(obstacle.checkNumberDislike()) {
							//si el vot té molts dislikes el borrem
							this.eliminar(idObstacle);
						}
						
						//Decrementem puntuació a l'usuari creador de l'obstacle
						Optional<Usuari> usuariCreadorE = usuariRepo.findById(obstacle.getIdUsuariCreador());
						if(usuariCreadorE.isPresent()) {
							Usuari usuariCreador = usuariCreadorE.get(); 
							usuariCreador.decrementarPuntuacio(1); // Decrementem 1 PUNT
							usuariRepo.save(usuariCreador);
						}
					}else {
						throw new TaskManagerBussinessException(ExceptionsCodes.OBS_ALREADY_VOTED, HttpStatus.NOT_FOUND,"usuari ja ha votat");
					}
				}
				
			}else {
				throw new TaskManagerBussinessException(ExceptionsCodes.USE_NOT_FOUND, HttpStatus.NOT_FOUND,"usuari_no_existeix");
			}	
		}else {
			throw new TaskManagerBussinessException(ExceptionsCodes.OBS_NOT_FOUND, HttpStatus.NOT_FOUND,
					"obstacle_no_existeix");
		}
	}

	public void treureLikeObstacle(int idObstacle, int idUsuariVotador) {
		this.treureVot(idObstacle, idUsuariVotador, true);
	}

	public void treureDislikeObstacle(int idObstacle, int idUsuariVotador) {
		this.treureVot(idObstacle, idUsuariVotador, false);
	}
	
	public void treureVot(int idObstacle, int idUsuariVotador, boolean esLike) {
		Optional<Obstacle> obstacleExistent =  obstacleRepo.findById(idObstacle);
		Optional<Usuari> usuariExistent =  usuariRepo.findById(idUsuariVotador);
		
		if(obstacleExistent.isPresent()) {
			Obstacle obstacle = obstacleExistent.get();
			if(usuariExistent.isPresent()) {
				Usuari usuariVotador = usuariExistent.get();
				
				if(esLike) { // treure Like a Objecte
					if(obstacle.getUsuarisLike().indexOf(usuariVotador) != -1) { // si l'usuari ha votat like anteriorment
						obstacle.getUsuarisLike().remove(usuariVotador);

						//Treiem puntuació a l'usuari creador de l'obstacle
						Optional<Usuari> usuariCreadorE = usuariRepo.findById(obstacle.getIdUsuariCreador());
						if(usuariCreadorE.isPresent()) {
							Usuari usuariCreador = usuariCreadorE.get(); 
							usuariCreador.decrementarPuntuacio(1); // Decrementem 1 PUNT
							usuariRepo.save(usuariCreador);
						}
					}
				}else { // treure Dislike a Objecte
					if(obstacle.getUsuarisDislike().indexOf(usuariVotador) != -1) { // si l'usuari ha votat dislike anteriorment
						obstacle.getUsuarisDislike().remove(usuariVotador);

						//Retornem puntuació a l'usuari creador de l'obstacle
						Optional<Usuari> usuariCreadorE = usuariRepo.findById(obstacle.getIdUsuariCreador());
						if(usuariCreadorE.isPresent()) {
							Usuari usuariCreador = usuariCreadorE.get(); 
							usuariCreador.incrementarPuntuacio(1); // Incrementem 1 PUNT
							usuariRepo.save(usuariCreador);
						}
					}
				}
			}
		}
	}

	public void solucionarObstacle(int idObstacle, int idUsuari) {
		Optional<Obstacle> obstacleExistent =  obstacleRepo.findById(idObstacle);
		Optional<Usuari> usuariExistent =  usuariRepo.findById(idUsuari);
		
		if(obstacleExistent.isPresent()) {
			Obstacle obstacle = obstacleExistent.get();
			if(usuariExistent.isPresent()) {
				Usuari usuariVotador = usuariExistent.get();
				if(obstacle.getUsuarisResolt().indexOf(usuariVotador) == -1) { // si l'usuari no ha votat solucioat anteriorment
					 //Afegim usuari que ha fet like a la llista de l'obstacle
					obstacle.getUsuarisResolt().add(usuariVotador);
				}else {
					throw new TaskManagerBussinessException(ExceptionsCodes.OBS_ALREADY_VOTED, HttpStatus.NOT_FOUND,"usuari ja ha solucionat l'obstacle");
				}
			}else {
				throw new TaskManagerBussinessException(ExceptionsCodes.USE_NOT_FOUND, HttpStatus.NOT_FOUND,"usuari_no_existeix");
			}
		}else {
			throw new TaskManagerBussinessException(ExceptionsCodes.OBS_NOT_FOUND, HttpStatus.NOT_FOUND,
					"obstacle_no_existeix");
		}
		
	}

	public void treureSsolucionarObstacle(int idObstacle, int idUsuari) {
		Optional<Obstacle> obstacleExistent =  obstacleRepo.findById(idObstacle);
		Optional<Usuari> usuariExistent =  usuariRepo.findById(idUsuari);
		if(obstacleExistent.isPresent()) {
			Obstacle obstacle = obstacleExistent.get();
			if(usuariExistent.isPresent()) {
				Usuari usuariVotador = usuariExistent.get();
				if(obstacle.getUsuarisResolt().indexOf(usuariVotador) != -1) { // si l'usuari ha votat result anteriorment
					obstacle.getUsuarisResolt().remove(usuariVotador);
				}
			}else {
				throw new TaskManagerBussinessException(ExceptionsCodes.USE_NOT_FOUND, HttpStatus.NOT_FOUND,"usuari_no_existeix");
			}
		}else {
			throw new TaskManagerBussinessException(ExceptionsCodes.OBS_NOT_FOUND, HttpStatus.NOT_FOUND,
					"obstacle_no_existeix");
		}
	}
		
	
	// - unvote de like i dislike
	// - Eliminar obstacle si s'ha votat 5 cops malament
	// - Votar i unvote de Resolved
	// Inserts de H2
	// Tutorial de puntuacio
	
	
	// CONFIG : Poder fer un push de el nombre de negatius que cal tenir per eliminar el obstacle
	
	
}
