package com.example.demo.repositoryTest;

import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Obstacle;
import com.example.demo.repository.ObstacleRepo;

//@Transactional
//@Rollback(false)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObstacleRepoTest {

//	private static final Logger LOG = LoggerFactory.getLogger(DefArquicDemoServiceTests.class);

	@Autowired
	ObstacleRepo obstacleRepo;
	
	Obstacle obs ;
	
	@Before
	 public void beforeEachTest (){
		obs = new Obstacle();
		obs.setNom("Escales");
		obs.setDescripcio("Moltes Escales");
		obs.setLatitud(123.23);
		obs.setLongitud(345.567);
		obs.setFotoUrl("www.foto.com");
		obs.setIdUsuariCreador(101);
	}
	
	int id ;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void insertObstacle_and_works() {
		id = obstacleRepo.save(obs).getId();
		Assert.assertTrue(obstacleRepo.findAll().size()> 0);
	}
	
	@Test
	public void findObject_and_works() {
		Optional<Obstacle> obstacleExistent = obstacleRepo.findById(id);
		Assert.assertTrue(obstacleExistent.isPresent()); 
		
	}
	
	@Test
	public void findByLongitudAndLatitud_and_works() {
		//Ja esxisteix obs creat en el pas anterior
		double lo = 345.567;
		double la = 123.23;
		Assert.assertTrue(obstacleRepo.findByLongitudAndLatitud(lo,la).isPresent());
	}
	
	@Test
	public void findByLongitudAndLatitud_and_NOT_works() {
		double lo = 55.55;
		double la = 44.44;
		Assert.assertFalse(obstacleRepo.findByLongitudAndLatitud(lo,la).isPresent());
	}
	
	
	@Test
	public void updateObstacle_and_works() {
		int id = obstacleRepo.save(obs).getId();
		Optional<Obstacle> obstacleExistent = obstacleRepo.findById(id);
		Assert.assertTrue(obstacleExistent.isPresent()); 
		Obstacle obstacleExistentobs =  obstacleExistent.get();
		obstacleExistentobs.setNom("Pendent 40");
		obstacleRepo.save(obstacleExistentobs);
		Assert.assertNotEquals(obs.getNom(), obstacleExistentobs.getNom());
		
	}



	
}







