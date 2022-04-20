package com.prakash.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.prakash.model.User;

@Component
public class UserService {

	private static List<User> users=new ArrayList<>();
	private static int countUser=3;
	
	static {
		users.add(new User(1,"Prakash",new Date()));
		users.add(new User(2,"deepti",new Date()));
		users.add(new User(3,"Aahana",new Date()));
	}
	
	public List<User> findAll()
	{
		return users;
	}
	
	public User save(User user)
	{
		if(user.getId()==null)
		{
			user.setId(++countUser);
			
		}
		users.add(user);
		return user;
	}
	
	public User findById(Integer id)
	{
		for (User user : users) {
			if(user.getId()==id)
			{
				return user;
			}
		}
		return null;
	}
	
	public User deleteUserById(Integer id)
	{
		Iterator<User> iterator=users.iterator();
		while(iterator.hasNext())
		{
			User user=iterator.next();
			if(user.getId()==id)
			{
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}

