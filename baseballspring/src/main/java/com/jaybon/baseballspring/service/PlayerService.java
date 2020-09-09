package com.jaybon.baseballspring.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.jaybon.baseballspring.dto.PositionDto;
import com.jaybon.baseballspring.dto.RankPlayerDto;
import com.jaybon.baseballspring.repository.PlayerRepsitory;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepsitory playerRepsitory;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<PositionDto> positionDtoList() {
		
		List<PositionDto> positionDtoList = em.createNamedQuery("mPositionPlayer").getResultList();

		return positionDtoList;
		
	}
	
	public List<RankPlayerDto> RankPlayerDtoList() {
		
		List<RankPlayerDto> rankPlayerList = em.createNamedQuery("mPlayer").getResultList();
		

		return rankPlayerList;
		
	}

}
