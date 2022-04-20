package com.prakash.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.prakash.model.SomeBean;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public  SomeBean fetchBean()
	{
		
		return new SomeBean("field1","field2","field3");
	}
	
	@GetMapping("/filtering-list")
	public  List<SomeBean> fetchBeanList()
	{
		
		return Arrays.asList(new SomeBean("field1","field2","field3"),
				new SomeBean("field12","field22","field32"));
	}
	
	
	@GetMapping("/filteringdynamic-list")
	public  MappingJacksonValue fetchBeanList2()
	{
		List<SomeBean> someBeanList=Arrays.asList(new SomeBean("field1","field2","field3"),
				new SomeBean("field12","field22","field32"));
		
		SimpleBeanPropertyFilter simpleBeanPropertyFilter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filterProvider=new SimpleFilterProvider().addFilter("someBean", simpleBeanPropertyFilter);
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(someBeanList);
		mappingJacksonValue.setFilters(filterProvider);
		
		return mappingJacksonValue;
	}
	

	@GetMapping("/filteringDynamic")
	public  MappingJacksonValue fetchBean2()
	{
		SomeBean someBean=new SomeBean("field1","field2","field3"); 
		SimpleBeanPropertyFilter simpleBeanPropertyFilter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filterProvider=new SimpleFilterProvider().addFilter("someBean", simpleBeanPropertyFilter);
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(someBean);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}
	
	@GetMapping("/filteringDynamic2")
	public  MappingJacksonValue fetchBean22()
	{
		SomeBean someBean=new SomeBean("field1","field2","field3"); 
		SimpleBeanPropertyFilter simpleBeanPropertyFilter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filterProvider=new SimpleFilterProvider().addFilter("someBean", simpleBeanPropertyFilter);
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(someBean);
		mappingJacksonValue.setFilters(filterProvider);
		return mappingJacksonValue;
	}
	
}
