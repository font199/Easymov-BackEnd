package com.example.demo.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.dto.PuntuacioDto;
import com.example.demo.dto.TokenResDto;
import com.example.demo.dto.UsuariDto;
import com.example.demo.dto.UsuariResDto;
import com.example.demo.entity.Obstacle;
import com.example.demo.rest.UsuariController;
import com.example.demo.service.UsuariService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UsuariController.class, secure = false)
public class UsuariControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	private TestEntityManager entityManager;
	
	@MockBean
	private UsuariService usuariService;
	
	Obstacle obs;
	UsuariDto uDto;
	TokenResDto tokenResDto;
	UsuariResDto usuariDto;
	PuntuacioDto puntuacioDto;
	
	@Before
	 public void beforeEachTest (){
		obs = new Obstacle();
		obs.setId(0);
		obs.setNom("Escales");
		obs.setDescripcio("Moltes Escales");
		obs.setLatitud(123.23);
		obs.setLongitud(345.567);
		obs.setFotoUrl("www.foto.com");
		obs.setIdUsuariCreador(101);
		
		usuariDto = new UsuariResDto();
		usuariDto.setId(1);
		usuariDto.setNom("test");
		usuariDto.setMail("testmail");
		usuariDto.setFotoURL("");
		usuariDto.setIdGoogle("");
		usuariDto.setPuntuacio(0);
		
		tokenResDto = new TokenResDto();
		tokenResDto.setId(1);
		tokenResDto.setToken("token");
		 puntuacioDto = new PuntuacioDto();
		puntuacioDto.setPuntuacio(10);
		//tokenResDto = mock(TokenResDto.class);
		//usuariDto = mock(UsuariResDto.class);
		//puntuacioDto.setPuntuacio(10);
	}
	
	@Test
	public void registrarUsuariTest() throws Exception {
		
		Mockito.when(
				usuariService.registrar(uDto)).thenReturn(tokenResDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/usuari").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{id:1,token: token}";
		
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
		
	}
	
	/*
	@Test
	public void getUsuarisTest() throws Exception{
		
		Mockito.when(
				usuariService.llistar()).thenReturn((List<UsuariDto>) tokenResDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/rest/usuaris").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
	}
	*/
	
	@Test
	public void getUsuariconcretTest()  throws Exception{
		entityManager.persist(usuariDto);
		entityManager.flush();
		
		Mockito.when(
				usuariService.buscar(Mockito.anyInt())).thenReturn((usuariDto));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/rest/usuari/{id}").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected =  "{id:1,nom:test,fotoURL:,idGoogle:,mail:testmail,puntuacio:0}";
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);

	}

	@Test
	public void puntuarUsuariconcretTest() throws Exception{
		
		  MockHttpServletRequest request = new MockHttpServletRequest();
	        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	         
	        Mockito.when(usuariService.puntuar(uDto.getId(),puntuacioDto )).thenReturn(puntuacioDto.getPuntuacio());
			
	         
	        int responseEntity = usuariService.puntuar(uDto.getId(), puntuacioDto);
	         
	        assertThat(responseEntity).isEqualTo(uDto.getId());
	    }
		
		/*
		Mockito.when(
				usuariService.puntuar(uDto.getId(),puntuacioDto )).thenReturn(puntuacioDto.getPuntuacio());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
				"/rest/puntuar/{id}").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals("10",result.getResponse().getContentAsString(),false);
		*/
	/*
	@Test
	public void getRankingUsuariTest()  throws Exception{
		Mockito.when(
				usuariService.ranking()).thenReturn(Mockito.anyList());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
				"/rest/ranking").accept(MediaType.APPLICATION_JSON);
		
		JSONAssert.assertEquals("10",result.getResponse().getContentAsString(),false);
		assertThat(
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		
	}
	*/


}
