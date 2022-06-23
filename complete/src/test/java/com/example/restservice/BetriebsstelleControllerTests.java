/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
public class BetriebsstelleControllerTests {

	@Autowired
	private MockMvc mockMvc;
	private String json = "{\"code\":\"aamp\",\"name\":\"hamburg anckelmannsplatz\",\"kurzname\":\"anckelmannsplatz\",\"typ\":\"üst\"}";

	@Test
	public void testRequestShoulBeSuccessful() throws Exception {
		 String content = this.mockMvc.perform(get("/betriebsstelle").param("code","aamp")).andDo(print()).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
	}

	@Test
	public void nonexistantCodeResultBadRequest() throws Exception {
		this.mockMvc.perform(get("/betriebsstelle").param("code", "aaaa"))
				.andDo(print()).andExpect(status().isBadRequest());
	}

}
