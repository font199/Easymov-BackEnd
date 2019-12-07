package com.example.demo.repositoryTest;

import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Obstacle;
import com.example.demo.repository.ObstacleRepo;

//@RunWith(SpringRunner.class) is used to provide a bridge between Spring Boot test features and JUnit.
//Whenever we are using any Spring Boot testing features in our JUnit tests, this annotation will be required.
//@DataJpaTest provides some standard setup needed for testing the persistence layer
@RunWith(SpringRunner.class)
@DataJpaTest
public class ObstacleRepoTest {

//	private static final Logger LOG = LoggerFactory.getLogger(DefArquicDemoServiceTests.class);

	@Autowired
	private TestEntityManager entityManager;
	 
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
	
	
	@Test
	public void insertObstacle_and_works() {
		//given
		entityManager.persist(obs);
		entityManager.flush();
		
		//when
		List<Obstacle> obstacles = obstacleRepo.findAll();
		
		//then
		Assert.assertTrue(obstacles.size() > 0);
	}
	
	@Test
	public void findObject_and_works() {	
		//given
		Obstacle obsCreat = entityManager.persist(obs);
		entityManager.flush();
		
		//when
		Optional<Obstacle> obstacleExistent = obstacleRepo.findById(obsCreat.getId());
		
		//then
		Assert.assertTrue(obstacleExistent.isPresent());
	}
	
	@Test
	public void findByLongitudAndLatitud_and_works() {
		//given
		entityManager.persist(obs);
		entityManager.flush();
		
		//when
		Optional<Obstacle> obstacleExistent = obstacleRepo.findByLongitudAndLatitud(obs.getLongitud(),obs.getLatitud());
		
		//then
		Assert.assertTrue(obstacleExistent.isPresent());
		Assert.assertEquals(obstacleExistent.get().getNom(), obs.getNom());
	}
	
	@Test
	public void findByLongitudAndLatitud_and_NOT_works() {
		//given
		double lo = 55.55;
		double la = 44.44;
		
		//when
		Optional<Obstacle> obstacleExistent = obstacleRepo.findByLongitudAndLatitud(lo,la);
		
		//then
		Assert.assertFalse(obstacleExistent.isPresent());
	}
	
	
	@Test
	public void updateObstacle_and_works() {
		//given
		Obstacle obsCreat = entityManager.persist(obs);
		entityManager.flush();
		
//		entityManager.
		//when
//		Optional<Obstacle> obstacleExistent = obstacleRepo.findById(obsCreat));
//		
//		//then
//		Assert.assertTrue(obstacleExistent.isPresent());
//
//		
//		
//		int id = obstacleRepo.save(obs).getId();
//		Optional<Obstacle> obstacleExistent = obstacleRepo.findById(id);
//		Assert.assertTrue(obstacleExistent.isPresent()); 
//		Obstacle obstacleExistentobs =  obstacleExistent.get();
//		obstacleExistentobs.setNom("Pendent 40");
//		obstacleRepo.save(obstacleExistentobs);
//		Assert.assertNotEquals(obs.getNom(), obstacleExistentobs.getNom());
		
	}



	
}







