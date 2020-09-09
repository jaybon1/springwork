package com.jaybon.baseballspring.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "player")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutPlayer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "reason")
	private String reason;
	
	@Column(name = "day")
	private String day;
	
	@CreationTimestamp
	@Column(name = "createDate")
	private Timestamp createDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "playerId")
	private Player player;
	
}
