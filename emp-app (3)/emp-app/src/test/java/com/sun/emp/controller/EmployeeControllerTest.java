package com.sun.emp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.emp.model.Employee;
import com.sun.emp.service.serviceImpl.EmployeeServiceImpl;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeServiceImpl serviceImpl;
	@Autowired
	private ObjectMapper mapper;

//	@Test
//	@DisplayName("Junit test for saveEmployee")
//	public void givenEmployee_whenSaveEmployee_returnEmployeeObject() throws JsonProcessingException, Exception {
//		Employee emp=new Employee();
//		emp.setId(3L);
//		emp.setName("sunil");
//		emp.setEmail("sunil123@gmail.com");
//		emp.setSal(435627.00);
//	 
//		//given
//	 given(serviceImpl.saveEmployee(emp))
//     .willAnswer((save)-> save.getArgument(0));
//		//when
//	ResultActions actions=mockMvc.perform(post("/employee/api/emp").contentType(MediaType.APPLICATION_JSON)
//		.content(mapper.writeValueAsString(emp)));
//	
//	//then
//	 actions.andDo(print())
//	.andExpect(status().isCreated())
//	.andExpect(jsonPath("$.name", is(emp.getName())))
//	.andExpect(jsonPath("$.email", is(emp.getEmail())))
//	.andExpect(jsonPath("$.sal", is(emp.getSal())));
//	
//	}

	@Test
	public void testPostExample() throws Exception {
		Employee emp = new Employee();
		emp.setId(1L);
		emp.setName("Arun");
		emp.setEmail("sunil123@gmail.com");
		emp.setSal(435627.00);
		Mockito.when(serviceImpl.saveEmployee(ArgumentMatchers.any())).thenReturn(emp);
		String json = mapper.writeValueAsString(emp);
		mockMvc.perform(post("/employee/api/emp").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", Matchers.equalTo(1)))
				.andExpect(jsonPath("$.name", Matchers.equalTo("Arun")));
	}
}
