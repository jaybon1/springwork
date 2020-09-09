package com.jaybon.baseballspring.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jaybon.baseballspring.dto.PositionDto;
import com.jaybon.baseballspring.dto.RankPlayerDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SqlResultSetMapping(
		name = "mPositionPlayer",
		classes = @ConstructorResult(
				targetClass = PositionDto.class,
				columns = {
						@ColumnResult(name = "position", type = String.class),
						@ColumnResult(name = "lotte", type = String.class),
						@ColumnResult(name = "hanhwa", type = String.class),
						@ColumnResult(name = "samsung", type = String.class)
				})
		)


@NamedNativeQuery(
        name = "mPositionPlayer",
        query = "SELECT position, " + 
        		"	MAX(IF(player.teamId = 1, player.name, \"\")) \"lotte\", " + 
        		"    MAX(IF(player.teamId = 2, player.name, \"\")) \"hanhwa\", " + 
        		"    MAX(IF(player.teamId = 3, player.name, \"\")) \"samsung\" " + 
        		"FROM player, team " + 
        		"WHERE player.teamId = team.id " + 
        		"	AND player.id NOT IN (SELECT player.id " + 
        		"							FROM player, outplayer " + 
        		"							WHERE outplayer.playerId = player.id) " + 
        		"GROUP BY position",
        resultSetMapping = "mPositionPlayer"
)

@Entity
@Getter
@Setter
@ToString(exclude = {"team", "outPlayer"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "position")
	private String position;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "teamId")
	@JsonIgnoreProperties({"players"})
	private Team team;
	
	@OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"player"})
	private OutPlayer outPlayer;
	
	@CreationTimestamp
	@Column(name = "createDate")
	private Timestamp createDate;
	
}
