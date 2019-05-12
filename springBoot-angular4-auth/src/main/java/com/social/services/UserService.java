package com.social.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.social.dao.ClientsRepository;
import com.social.dao.OrderRepository;
import com.social.dao.TimeSheetRepository;
import com.social.dao.UserRepository;
import com.social.entities.Clients;
import com.social.entities.Order;
import com.social.entities.TimeSheet;
import com.social.entities.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TimeSheetRepository timeSheetRepository;
	
	@Autowired
	ClientsRepository clientsRepository;
	
	@Autowired
	OrderRepository orderRepository;

	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public User find(String userName) {
		return userRepository.findOneByUsername(userName);
	}

	public User find(Long id) {
		return userRepository.findOne(id);
	}

	public List<TimeSheet> findByProfile(String profile) {
		return timeSheetRepository.findOneByProfile(profile);
	}

	public List<Clients> findClientByProfile(String profile) {
		return clientsRepository.findOneByProfile(profile);
	}

	public Page<Clients> findByUserId(Long userId, Pageable pageable) {
		return clientsRepository.findByUserId(userId,pageable);
	}

	public Iterable<Clients> getAllClients() {
		return clientsRepository.findAll();
	}

	public void saveClient(Clients client) {
		clientsRepository.save(client);
		
	}

	public Clients findById(long client_id) {
		return clientsRepository.findOne(client_id);
	}

	public void deleteClient(long client_id) {
		clientsRepository.delete(client_id);
		
	}

	public void updateClient(Clients clients) {
		clientsRepository.save(clients);
		
	}

	public List<Order> searchByProfileAndOrderId(long order_id, String profile) {
		
		return orderRepository.findByProfileAndOrderId(order_id,profile);
		
	}

	public List<Order> searchByKeyword(String keyword) {
		
		return orderRepository.findBySearchTerm(keyword);
	}

	public long countByClient(String client_name) {
		// TODO Auto-generated method stub
		return orderRepository.countByClientName(client_name);
	}
}
