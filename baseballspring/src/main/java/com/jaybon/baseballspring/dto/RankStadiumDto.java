package com.jaybon.baseballspring.dto;

import com.jaybon.baseballspring.model.Stadium;

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
public class RankStadiumDto {
	
	private int rank;
	
	private Stadium stadium;

}
