package com.jaybon.baseballspring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jaybon.baseballspring.model.Player;

public interface PlayerRepsitory extends JpaRepository<Player, Integer> {

//	@Query(value = "SELECT position, " + 
//			"	MAX(IF(player.teamId = 1, player.name, \"\")) \"lotte\", " + 
//			"    MAX(IF(player.teamId = 2, player.name, \"\")) \"hanhwa\", " + 
//			"    MAX(IF(player.teamId = 3, player.name, \"\")) \"samsung\" " + 
//			"FROM player, team " + 
//			"WHERE player.teamId = team.id " + 
//			"GROUP BY position", nativeQuery = true)
//	List<PositionDto> mPositionPlayer();
	
	List<Player> findByTeamId(int teamId);
	
	List<Player> findAllByOrderByName();
	
	@Modifying
    @Transactional
	@Query(value = "delete from player where teamId = ?1", nativeQuery = true)
	void mDeleteByTeamId(int id);
	
	@Modifying
    @Transactional
	@Query(value = "delete from player where id = ?1", nativeQuery = true)
	void mDeleteById(int id);

}
