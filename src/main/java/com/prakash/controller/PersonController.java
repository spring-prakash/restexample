package com.prakash.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.model.Name;
import com.prakash.model.PersonV1;
import com.prakash.model.PersonV2;

@RestController
public class PersonController {

	@GetMapping("v1/person")
	public PersonV1 personV1()
	{
		return new PersonV1("Thorat Prakash");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2()
	{
		return new PersonV2(new Name("Prakash","Thorat"));
	}
	
	@GetMapping(value="person/param",params = "version=1")
	public PersonV1 paramsV1()
	{
		return new PersonV1("Thorat Prakash");
	}
	
	@GetMapping(value="person/param",params = "version=2")
	public PersonV2 paramsV2()
	{
		return new PersonV2(new Name("Prakash","Thorat"));
	}
	
	
	@GetMapping(value="person/header",headers  = "X-API-VERSION=1")
	public PersonV1 headerV1()
	{
		return new PersonV1("Thorat Prakash");
	}
	
	@GetMapping(value="person/header",headers = "X-API-VERSION=2")
	public PersonV2 headerV2()
	{
		return new PersonV2(new Name("Prakash","Thorat"));
	}
	

	@GetMapping(value="person/produces",produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1()
	{
		return new PersonV1("Thorat Prakash");
	}
	
	@GetMapping(value="person/produces",produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2()
	{
		return new PersonV2(new Name("Prakash","Thorat"));
	}
}
