package com.ngueno.rest.webservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ngueno.rest.webservices.example.bean.SomeBean;
import com.ngueno.rest.webservices.example.bean.SomeOtherBean;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("value1", "value2", "value3");
	}

	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBean() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("123", "234", "345"));
	}

	@GetMapping("/filtering-other")
	public SomeOtherBean retrieveSomeOtherBean() {
		return new SomeOtherBean("value1", "value2", "value3");
	}

	@GetMapping("/filtering-other-list")
	public List<SomeOtherBean> retrieveListOfSomeOtherBean() {
		return Arrays.asList(new SomeOtherBean("value1", "value2", "value3"), new SomeOtherBean("123", "234", "345"));
	}

	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue retrieveDinamycFilteredSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);

		return mapping;
	}

	@GetMapping("/dynamic-list-filtering")
	public MappingJacksonValue retrieveDinamycFilteredListOfSomeBean() {
		List<SomeBean> someBeanList = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("123", "234", "345"));

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(someBeanList);
		mapping.setFilters(filters);

		return mapping;
	}

}
