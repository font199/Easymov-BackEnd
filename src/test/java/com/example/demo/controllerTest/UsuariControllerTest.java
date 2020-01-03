package com.example.demo.controllerTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Obstacle;
import com.example.demo.entity.Usuari;
import com.example.demo.rest.UsuariController;
import com.example.demo.service.UsuariService;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuariController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuariControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	  private UsuariService osbtacleService;
	
	//Obstacle mockObstacle = new Obstacle(0,"","",0.0,0.0,"",1);
	

	@Test
	public void contexLoads() throws Exception {
		//assertThat(controller).isNotNull();
	}

}
