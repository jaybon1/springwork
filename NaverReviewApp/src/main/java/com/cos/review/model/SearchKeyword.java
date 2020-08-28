package com.cos.review.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchKeyword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String keyword;
}
