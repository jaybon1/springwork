package com.jaybon.baseballspring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jaybon.baseballspring.model.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, Integer> {
	
	List<Stadium> findAllByOrderByName();
	
	@Modifying
    @Transactional
	@Query(value = "delete from stadium where id = ?1", nativeQuery = true)
	void mDeleteById(int id);

}
