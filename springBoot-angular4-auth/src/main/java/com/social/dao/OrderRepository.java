package com.social.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.social.entities.Clients;
import com.social.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

	List<Order> findByProfile(String profile);
	
	//===============find by two parameter===============
	@Query("SELECT u FROM Order u WHERE u.order_id = ?1 AND u.profile= ?2")
	List<Order> findByProfileAndOrderId(long order_id, String profile);
	
	  @Query("SELECT t FROM Order t WHERE " +
	            "LOWER(t.client_name) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
	            "LOWER(t.project_number) LIKE LOWER(CONCAT('%',:searchTerm, '%'))")
	    List<Order> findBySearchTerm(@Param("searchTerm") String searchTerm);
	  
	  		// List<Order> findByProjectContainsOrClientContainsAllIgnoreCaseOrderByClientAsc(String project_number,
          //    String client_name);
	  
	  //===============count query========================
	  @Query("SELECT COUNT(u) FROM Order u WHERE u.client_name=?1")
	  Long countByClientName(String client_name);
	  
	  @Query(value = "SELECT * FROM Order WHERE client_name = ?1",
			    countQuery = "SELECT count(*) FROM Order WHERE client_name = ?1",
			    nativeQuery = true)
			  Page<Order> findByLastname(String client_name, Pageable pageable);
	  
		  //long countBySalaryGreaterThanEqual(int salary);
		  //long countByNameEndingWith(String endString);
		  //long countByNameLike(String likeString);
	  
	  //==============sorting query=====================
	  @Query("select u from Order u where u.client_name like ?1%")
	  List<Order> findByAndSort(String client_name, Sort sort);

	  @Query("select u.id, LENGTH(u.client_name) as fn_len from Order u where u.client_name like ?1%")
	  List<Object[]> findByAsArrayAndSort(String client_name, Sort sort);
}
