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

import com.example.demo.rest.ObstacleController;
import com.example.demo.service.ObstacleService;

@RunWith(SpringRunner.class)
@WebMvcTest(ObstacleController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ObstacleControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	  private ObstacleService osbtacleService;

	@Test
	public void contexLoads() throws Exception {
		//assertThat(controller).isNotNull();
	}

}
