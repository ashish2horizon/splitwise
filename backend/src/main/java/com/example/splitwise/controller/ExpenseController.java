package com.example.splitwise.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.splitwise.model.Expense;
import com.example.splitwise.model.Friends;
import com.example.splitwise.model.User;
import com.example.splitwise.model.Activity;
import com.example.splitwise.repository.ActivityRepository;
import com.example.splitwise.repository.ExpenseRepository;
import com.example.splitwise.repository.UserRepository;;

@RestController
@RequestMapping()
@CrossOrigin(origins = "http://localhost:4200")
public class ExpenseController {
	
	@Autowired
	ExpenseRepository expenseRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ActivityRepository activityRepository;
	
	@GetMapping("/activity")
	public List<Expense> getAllBooks() {
		List<Expense> list= expenseRepository.findAll();
		return list;
	}
	
	/*
	 * @GetMapping("/products/{id}") public Products getBookById(@PathVariable("id")
	 * ObjectId id) { System.out.println("id"); return
	 * serverService.findBookById(id); }
	 */
	@PostMapping("/expense")
	  public void addBook( @RequestBody Expense expense) {
		//books.set_id(ObjectId.get());
		//System.out.println("Id:"+books.get_id());
	//	System.out.println("expense"+expense.toString());
		List<String> name=Arrays.asList(expense.getSplit());
		List<String> filter=new ArrayList<String>(); 
		List<User> filterUser=new ArrayList<User>(); 
		  List<User> users=userRepository.findAll(); 
		  for(String str:name) {
			  filterUser.add(userRepository.findByFirstName(str));
		  }
		  System.out.println("filteredUser"+filterUser.toString());
		  boolean bool=name.contains(expense.getPaidBy());
		  boolean bool2;
		/*
		 * if(name.size()==users.size()) bool2=true; else bool2=false;
		 */
		  if(bool==false) {
			  System.out.println("if");
			  int price=(expense.getPrice())/(filterUser.size()); 
			  //System.out.println(price);
			  for(User user: filterUser ) { 
				  int cp=user.getOwe(); 
				  user.setOwe(price+cp);
			  userRepository.save(user); 
			  // update activty
			  Activity act=new Activity();
			  act.setDescription(expense.getDescription());	  
			  act.setOwes(price);
			  act.setOwesTo(expense.getPaidBy());
			  act.setFirstName(user.getFirstName());
			  act.setOwed(0);
			  act.setOwedBy(null);
			  activityRepository.save(act);		  
			  }
			  User user=userRepository.findByFirstName(expense.getPaidBy());
			  int cp=user.getOwed(); 
			  user.setOwed(expense.getPrice()+cp);
			  userRepository.save(user);			
		  }
		  else {
			  System.out.println("else");
			  int price=(expense.getPrice())/(filterUser.size()); 
			  //System.out.println(price);
			  for(User user: filterUser ) { 
				  int cp=user.getOwe(); 
				  user.setOwe(price+cp);
			  userRepository.save(user); 
			  // activity
			  Activity act=new Activity();
			  act.setDescription(expense.getDescription());	  
			  act.setOwes(price);
			  act.setOwesTo(expense.getPaidBy());
			  act.setFirstName(user.getFirstName());
			  act.setOwed(0);
			  act.setOwedBy(null);
			  activityRepository.save(act);	
			  }
			  User user=userRepository.findByFirstName(expense.getPaidBy());
			  int cp=user.getOwed(); 
			  user.setOwed(expense.getPrice()+cp-price);
			  userRepository.save(user);		  
		  }
		
		 
		 
	     expenseRepository.save(expense);     
	  }
	
	@GetMapping("/user")
	public List<User> getAllUser() {
		List<User> users= userRepository.findAll();
		return users;
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user) {
		User user2=new User();
		try {
			user2= userRepository.findByEmail(user.getEmail());
			if(user.getEmail().equals(user2.getEmail()) && user.getPassword().equals(user2.getPassword()))
				return user2;
		}
		catch (NullPointerException e) {
			e.getMessage();
			// TODO: handle exception
			System.out.println("catch");
		//	return user;
			
		}
		System.out.println("finally");
		user2.setEmail(user.getEmail());
		return user2;	
	}
	
	@PostMapping("/friends")
	public HashMap<String,Integer> getDetails(@RequestBody User user) {
		Friends friend=new Friends();
		user.setFirstName(user.getFirstName());
		HashMap<String,Integer> map=new HashMap<String, Integer>();
		List<Activity> act=activityRepository.findAll();
		for(Activity activity:act) {
			if(user.getFirstName().equals(activity.getFirstName()) &&  !(user.getFirstName().equals(activity.getOwesTo()))  ) {
				if(map.containsKey(activity.getOwesTo())) {
					int owe=activity.getOwes();
					int total=owe+map.get(activity.getOwesTo());
					map.replace(activity.getOwesTo(), total);
				}
				else {
					map.put(activity.getOwesTo(),activity.getOwes());
					
				}
			}
			
		}
		friend.setMap(map);
		
	    return map;
	}
	
	@PostMapping("/friends2")
	public HashMap<String, Integer> getDetails2( @RequestBody com.example.splitwise.model.User user) {
		Friends friend=new Friends();
		user.setFirstName("Ashish");
		HashMap<String,Integer> map=new HashMap<String, Integer>();
		List<Activity> act=activityRepository.findAll();
		for(Activity activity:act) {
			if( user.getFirstName().equals(activity.getOwesTo())  &&  !(user.getFirstName().equals(activity.getFirstName()))  ) {
				if(map.containsKey(activity.getFirstName())) {
					int owe=activity.getOwes();
					int total=owe+map.get(activity.getFirstName());
					map.replace(activity.getFirstName(), total);
				}
				else {
					map.put(activity.getFirstName(),activity.getOwes());
					
				}
			}
			
		}
		friend.setMap(map);
		
	    return map;
	}
	
	@PostMapping("/details")
	public List<Activity> getDetail(@RequestBody User user) {
		System.out.println("body"+user.getFirstName());
		List<Activity> detail=new ArrayList<Activity>();
		List<Activity> act=activityRepository.findAll();
		for(Activity activity:act) {
			if (user.getFirstName().equals(activity.getFirstName())/* && name.equals(activity.getOwesTo()) */) {
				detail.add(activity);
				}			
		}
				
	    return detail;
	}
	

}
