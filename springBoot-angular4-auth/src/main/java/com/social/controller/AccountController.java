package com.social.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.entities.Clients;
import com.social.entities.Order;
import com.social.entities.TimeSheet;
import com.social.entities.User;
import com.social.services.UserService;
import com.social.util.CustomErrorType;

@RestController
@RequestMapping("account")
public class AccountController {

	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private UserService userService;

	// request method to create a new account by a guest
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
		if (userService.find(newUser.getUsername()) != null) {
			logger.error("username Already exist " + newUser.getUsername());
			return new ResponseEntity(
					new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
					HttpStatus.CONFLICT);
		}
		newUser.setRole("USER");
		
		return new ResponseEntity<User>(userService.save(newUser), HttpStatus.CREATED);
	}

	// this is the login api/service
	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		return principal;
	}
	
	@RequestMapping(value = "/timesheet/{profile}", method = RequestMethod.GET)
	public ResponseEntity<?> getSingleTimeSheet(
			@PathVariable("profile") String profile) {
		logger.info("Fetching Timesheet with user {}", profile);
		List<TimeSheet> user = userService.findByProfile(profile);
		if (user == null) {
			logger.error("timeSheet with user {} not found.", user);
			return new ResponseEntity(new CustomErrorType(
					"timeSheet with user " + user + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<TimeSheet>>(user, HttpStatus.OK);
	}
	
	// ------------------- List Clients
		// -------------------------------------------
		@GetMapping(path = "/clients")
		public @ResponseBody Iterable<Clients> getAllClient() {

			return userService.getAllClients();
		}

		// ------------------- save Clients
		// -------------------------------------------

		@RequestMapping(value = "/save/clients", method = RequestMethod.POST)
		public ResponseEntity<?> createClient(@Valid @RequestBody Clients client) {
			logger.info("-------> save client by id : {}", client);

			System.out.println("---------------client created---------------");
			userService.saveClient(client);

			HttpHeaders headers = new HttpHeaders();

			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		
	// -------------------Retrieve Single
		// Client by Profile---------------------

		@RequestMapping(value = "/clients/{profile}", method = RequestMethod.GET)
		public ResponseEntity<?> getClientsByProfile(
				@PathVariable("profile") String profile) {
			logger.info("Fetching Client with user {}", profile);
			List<Clients> client = userService.findClientByProfile(profile);
			
			if (client == null) {
				
				logger.error("TimeSheet With User {} not found.", client);
				return new ResponseEntity(new CustomErrorType(
						"timeSheet with user " + client + " not found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Clients>>(client, HttpStatus.OK);
		}
		
		
		// -------------------Retrieve Single
		// Client------------------------------------------

		@RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getSingleClient(@PathVariable("id") long client_id) {
			logger.info("Fetching Client with id {}", client_id);
			Clients client = userService.findById(client_id);
			if (client == null) {
				logger.error("Client with id {} not found.", client_id);
				return new ResponseEntity(new CustomErrorType("Client with id "
						+ client_id + " not found"), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Clients>(client, HttpStatus.OK);
		}
		
		// ------------------- Delete a Client
		// -----------------------------------------

		@RequestMapping(value = "/client/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteClient(@PathVariable("id") long client_id) {
			logger.info("Fetching & Deleting Client with id {}", client_id);

			Clients client = userService.findById(client_id);
			if (client == null) {
				logger.error("Unable to delete. Client with id {} not found.",
						client_id);
				return new ResponseEntity(new CustomErrorType(
						"Unable to delete. Client with id " + client_id
								+ " not found."), HttpStatus.NOT_FOUND);
			}
			userService.deleteClient(client_id);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}

		// ------------------- Update a Client
		// ------------------------------------------------

		@RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateClient(@PathVariable("id") long client_id,
				@RequestBody Clients client) {
			logger.info("Updating User with id {}", client_id);

			Clients clients = userService.findById(client_id);

			if (clients == null) {
				logger.error("Unable to update. client with id {} not found.",
						client_id);
				return new ResponseEntity(new CustomErrorType(
						"Unable to upate. client with id " + client_id
								+ " not found."), HttpStatus.NOT_FOUND);
			}

			clients.setId(client.getId());
			clients.setClient_name(client.getClient_name());
			clients.setEmail(client.getEmail());
			clients.setProfile(client.getProfile());
			clients.setOrder(client.getOrder());
			clients.setUser(client.getUser());
			
			userService.updateClient(clients);
			return new ResponseEntity<Clients>(clients, HttpStatus.OK);
		}
		
		@CrossOrigin
		@GetMapping("/test")
		@ResponseBody
		public String getFoos(@RequestParam String id) {
		    return "ID: " + id;
		}
	
		@CrossOrigin
		@GetMapping("/find")
		@ResponseBody
		public ResponseEntity<List<Order>> findBy(@RequestParam(name = "id") long order_id, 
				@RequestParam(name="profile") String profile) {
			logger.info("-------> find order by id : {}", profile);

			System.out.println("---------------client created---------------");
			List<Order> fetchePofileOrderId =  userService.searchByProfileAndOrderId(order_id,profile);

			HttpHeaders headers = new HttpHeaders();

			return new ResponseEntity<>(fetchePofileOrderId,headers, HttpStatus.OK);
		}
		
		@CrossOrigin
		@GetMapping("/search")
		@ResponseBody
		public ResponseEntity<List<Order>> search(@RequestParam(name="searchTerm") String keyword) {
			logger.info("-------> search order by id : {}", keyword);

			System.out.println("---------------client created---------------");
			List<Order> fetchePofileOrderId =  userService.searchByKeyword(keyword);

			HttpHeaders headers = new HttpHeaders();

			return new ResponseEntity<>(fetchePofileOrderId,headers, HttpStatus.OK);
		}
		
		
		@CrossOrigin
		@GetMapping("/count")
		@ResponseBody
		public ResponseEntity<Long> Count(@RequestParam(name="clientName") String client_name) {
			logger.info("-------> search order by id : {}", client_name);

			System.out.println("---------------client created---------------");
			long fetchePofileOrderId =  userService.countByClient(client_name);

			HttpHeaders headers = new HttpHeaders();

			return new ResponseEntity<>(fetchePofileOrderId,headers, HttpStatus.OK);
		}
		
	
}
