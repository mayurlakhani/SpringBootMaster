package com.social.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.entities.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{

}
