package com.prakash.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(path = "/hello")
	public String hello()
	{
		return "Spring hello prakash";
	}
	
	@GetMapping(path ="/helloBean")
	public HelloWorldBean helloBean()
	{
		return new HelloWorldBean("Prakash Thorat");
	}
	
	@GetMapping(path = "/hello/pathvariable/{name}")
	public String pathVariable(@PathVariable String name)
	{
		return "Name="+name;
	}
	
	@GetMapping(path = "/hello/pathvariable2/{name}")
	public HelloWorldBean hellopath(@PathVariable String name)
	{
		return new HelloWorldBean("Name="+name);
	}
}
