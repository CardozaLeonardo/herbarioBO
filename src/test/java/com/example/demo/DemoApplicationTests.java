package com.example.demo;

import controlador.DemoApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = DemoApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DemoApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	void contextLoads() throws Exception {

		Cookie[] cookies = new Cookie[2];
		cookies[0] = new Cookie("token-access", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNTg5MTY4NjM1LCJqdGkiOiI2MTY5ZDI2ODFlMjA0YTUzYjBiNmMwMTc3MTlmNWQwMyIsInVzZXJfaWQiOjY0fQ.0BUrvAJJKUEDD0RVT9X_eGvcjN9sU8eXX1sf-jhwHO8");
		cookies[1] = new Cookie("token-refresh", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6MTU4OTE4OTkzNSwianRpIjoiNmM0MDdlNGQwNjA4NDkyMWE3MTkyMGE2NDYwMGY1MDYiLCJ1c2VyX2lkIjo2NH0.pbooKk25nI82_VV3C_qSE7ro6IS6JsHFPJOADojxj7E");

		this.mockMvc.perform(MockMvcRequestBuilders.get("/fichas/PlantList",1).cookie(cookies)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

}
