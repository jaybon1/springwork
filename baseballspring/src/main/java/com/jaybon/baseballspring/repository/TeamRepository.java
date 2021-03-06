package com.jaybon.baseballspring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jaybon.baseballspring.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
	
	List<Team> findAllByOrderByName();
	
	@Modifying
    @Transactional
	@Query(value = "delete from team where id = ?1", nativeQuery = true)
	void mDeleteById(int id);

}
