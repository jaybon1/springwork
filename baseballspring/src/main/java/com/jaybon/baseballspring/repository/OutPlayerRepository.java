package com.jaybon.baseballspring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jaybon.baseballspring.model.OutPlayer;

public interface OutPlayerRepository extends JpaRepository<OutPlayer, Integer>{
	
	@Modifying
    @Transactional
	@Query(value = "delete from outplayer where PlayerId = ?1", nativeQuery = true)
	void mDeleteByPlayerId(int id);

}
