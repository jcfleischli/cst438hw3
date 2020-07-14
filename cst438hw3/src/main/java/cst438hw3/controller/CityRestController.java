package cst438hw3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cst438hw3.domain.*;
import cst438hw3.service.CityService;

@RestController
public class CityRestController {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/api/cities/{city}")
	public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {
		
		// calls the cityService to get info on the input cityName.
		CityInfo city = cityService.getCityInfo(cityName);
		
		// if response is null, returns 404 NOT FOUND, else return city info and 200 OK
		if (city != null) {
			return new ResponseEntity<CityInfo>(city, HttpStatus.OK);
		} else {
			return new ResponseEntity<CityInfo>(HttpStatus.NOT_FOUND);
		}
	}
	
}
