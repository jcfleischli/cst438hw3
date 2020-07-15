package cst438hw3.service;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;

import cst438hw3.domain.*;
import cst438hw3.service.CityService;
import cst438hw3.service.WeatherService;
 
@SpringBootTest
public class CityServiceTest {

	@MockBean
	private WeatherService weatherService;
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;

	
	@Test
	public void contextLoads() {
	}


	@Test // case when there is a single city with the input name.
	public void testCityFound() throws Exception {

		Country country = new Country("TST", "Test Country");
		City city = new City(1, "TestCity", country.getCode(),"DistrictTest", 100000);
		List<City> cities = new ArrayList<City>();
		cities.add(city);
		TimeAndTemp timeAndTemp = new TimeAndTemp(300, 1593900461, -14400);
		
		// create stub calls and returns the data for the city and country repository and the weather service.
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		given(countryRepository.findByCode(city.getCountryCode())).willReturn(country);
		given(weatherService.getTimeAndTemp("TestCity")).willReturn(timeAndTemp);
		
		// perform test by calling getCityInfo method with a city name that exists.
		CityInfo cityResult = cityService.getCityInfo("TestCity");
		
		// sets the expected results.
		CityInfo expectedResult = new CityInfo(1, "TestCity", "TST", "Test Country", "DistrictTest", 100000, 80, "3:07 PM");
		
		// checks that the returned results match the expected results
		assertThat(cityResult).isEqualTo(expectedResult);
	}
	
	@Test // case when there are not any cities with the input name.
	public void  testCityNotFound() throws Exception {

		Country country = new Country("TST", "Test Country");
		City city = new City(1, "TestCity", country.getCode(),"DistrictTest", 100000);
		List<City> cities = new ArrayList<City>();
		cities.add(city);
		
		// create the stub call and return data for city repository.
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		
		// perform test by calling getCityInfo with a city name parameter that doesn't exist.
		CityInfo cityResult = cityService.getCityInfo("FalseCity");
		
		// checks that the returned results are set to null.
		assertNull(cityResult);
	}
	
	@Test // case when there are multiple cities with the input name.
	public void  testCityMultiple() throws Exception {
		
		// setup multiple cities with the same names.
		Country country1 = new Country("TST1", "Test Country1");
		Country country2 = new Country("TST2", "Test Country2");
		Country country3 = new Country("TST3", "Test Country3");
		
		City city1 = new City(1, "TestCity", country1.getCode(),"DistrictTest1", 100000);
		City city2 = new City(2, "TestCity", country2.getCode(),"DistrictTest2", 200000);
		City city3 = new City(3, "TestCity", country3.getCode(),"DistrictTest3", 300000);
		
		List<City> cities = new ArrayList<City>();
		cities.add(city1);
		cities.add(city2);
		cities.add(city3);
		
		TimeAndTemp timeAndTemp = new TimeAndTemp(300, 1593900461, -14400);
		
		// create stub calls and returns the data for the city and country repository and the weather service.
		given(cityRepository.findByName("TestCity")).willReturn(cities);
		given(countryRepository.findByCode(cities.get(0).getCountryCode())).willReturn(country1);
		given(weatherService.getTimeAndTemp("TestCity")).willReturn(timeAndTemp);
		
		// perform test by calling getCityInfo method with a city name that exists multiple times.
		CityInfo cityResult = cityService.getCityInfo("TestCity");
		
		// sets the expected results to be the first city added to the cities ArrayList.
		CityInfo expectedResult = new CityInfo(1, "TestCity", "TST1", "Test Country1", "DistrictTest1", 100000, 80, "3:07 PM");
		
		// checks that the returned results match the expected results
		assertThat(cityResult).isEqualTo(expectedResult);
		
	}

}
