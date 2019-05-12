package com.social.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.entities.TimeSheet;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long>{

	List<TimeSheet> findOneByProfile(String profile);
	

}
