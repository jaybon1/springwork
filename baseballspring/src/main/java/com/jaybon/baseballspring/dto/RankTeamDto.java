package com.jaybon.baseballspring.dto;

import com.jaybon.baseballspring.model.Team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankTeamDto {

	private int rank;
	
	private Team team;
	
}
