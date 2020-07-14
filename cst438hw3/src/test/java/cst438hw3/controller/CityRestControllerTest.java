package cst438hw3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import cst438hw3.controller.CityRestController;
import cst438hw3.domain.*;
import cst438hw3.service.CityService;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {

	@MockBean
	private CityService cityService;

	@Autowired
	private MockMvc mvc;

	// This object will be magically initialized by the initFields method below.
	private JacksonTester<CityInfo> json;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testCityFound() throws Exception {
		
		City city = new City(1, "TestCity", "TST", "DistrictTest", 100000);
		
		// create stub calls and return data for CityRestController.
		given(cityService.getCityInfo("TestCity")).willReturn(new CityInfo(city, "TestCountry", 80.12, "2:50 PM"));
		
		// run the test using simulated http call.
		MockHttpServletResponse response = mvc.perform(get("/api/cities/TestCity")).andReturn().getResponse();
		
		// Verify that the response status was 200 OK.
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		// converts the cityResults from json to string.
		CityInfo cityResult = json.parseObject(response.getContentAsString());
		
		// sets the expected results.
		CityInfo expectedResult = new CityInfo(1, "TestCity", "TST", "TestCountry", "DistrictTest", 100000, 80.12, "2:50 PM");
		
		// checks that the returned results match the expected results
		assertThat(cityResult).isEqualTo(expectedResult);
	}
	
	@Test
	public void testCityNotFound() throws Exception {
		
		City city = new City(1, "TestCity", "TST", "DistrictTest", 100000);
		
		// create stub calls and return data for CityRestController.
		given(cityService.getCityInfo("TestCity")).willReturn(new CityInfo(city, "TestCountry", 80.12, "2:50 PM"));
		
		// run the test using simulated http call with a fake city name that is not in the database.
		MockHttpServletResponse response = mvc.perform(get("/api/cities/FalseCity")).andReturn().getResponse();
		
		// Verify that the response status was 404 NOT FOUND.
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

}
