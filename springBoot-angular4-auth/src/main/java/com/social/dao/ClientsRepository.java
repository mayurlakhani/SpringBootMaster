package com.social.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.entities.Clients;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long>{

	//List<Clients> findByProfile(String profile);

	//@Query("SELECT u FROM Clients u WHERE u.client_name = ?1")
	//Clients findClientsByClientName(String client_name);

	List<Clients> findOneByProfile(String profile);

	Page<Clients> findByUserId(Long userId, Pageable pageable);

}
