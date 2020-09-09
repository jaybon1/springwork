package com.jaybon.baseballspring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jaybon.baseballspring.dto.PositionDto;
import com.jaybon.baseballspring.dto.RankPlayerDto;
import com.jaybon.baseballspring.dto.RankStadiumDto;
import com.jaybon.baseballspring.dto.RankTeamDto;
import com.jaybon.baseballspring.model.OutPlayer;
import com.jaybon.baseballspring.model.Player;
import com.jaybon.baseballspring.model.Stadium;
import com.jaybon.baseballspring.model.Team;
import com.jaybon.baseballspring.repository.OutPlayerRepository;
import com.jaybon.baseballspring.repository.PlayerRepsitory;
import com.jaybon.baseballspring.repository.StadiumRepository;
import com.jaybon.baseballspring.repository.TeamRepository;
import com.jaybon.baseballspring.service.PlayerService;

@Controller
public class TestController {
	
	@Autowired
	private StadiumRepository stadiumRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PlayerRepsitory playerRepsitory;
	
	@Autowired
	private OutPlayerRepository outPlayerRepository;
	
	@Autowired
	private PlayerService playerService;
	
	
	// 아구장 등록 페이지
	@GetMapping({"test"})
	public String test() {
		
//		playerService.test();
		
//		List<PositionDto> positionDtos = playerRepsitory.mPositionPlayer();
//		
//		System.out.println(positionDtos);
		
		return "stadiumAdd";
	}
	
	// 아구장 등록 페이지
	@GetMapping({"stadiumAdd"})
	public String stadiumAdd() {
		
		return "stadiumAdd";
	}
	
	// 야구장 등록하기
	@PostMapping({"stadium"})
	public String stadiumAddProc(Stadium stadium, Model model) {
		
		stadiumRepository.save(stadium);
		
		model.addAttribute("stadiumList", stadiumRepository.findAll());
		
		return "stadiumAdd";
	}
	
	// 팀 등록 페이지
	@GetMapping({"teamAdd"})
	public String teamAdd(Model model) {
		
		List<Stadium> stadiumList = new ArrayList<>();
		
		List<Stadium> stadiumListEntity = stadiumRepository.findAll();
		
		// 야구장 중에서 팀이 없는 야구장만 선택
		for (Stadium stadium : stadiumListEntity) {
			if(stadium.getTeam() == null) {
				stadiumList.add(stadium);
			}
		}
		
		model.addAttribute("stadiumList", stadiumList);
		
		return "teamAdd";
	}
	
	// 팀 등록하기
	@PostMapping({"team"})
	public String teamAddProc(Team team, int stadiumId, Model model) {
		
		team.setStadium(Stadium.builder().id(stadiumId).build());
		
		teamRepository.save(team);
		
		model.addAttribute("teamList", teamRepository.findAll());
		
		return "teamAdd";
	}
	
	// 선수 등록 페이지
	@GetMapping({"playerAdd"})
	public String playerAdd(Model model) {
		
		model.addAttribute("teamList", teamRepository.findAll());
		
		return "playerAdd";
	}
	
	// 선수 등록하기
	@PostMapping({"player"})
	public String playerAddProc(Player player, int teamId, Model model) {
		
		player.setTeam(Team.builder().id(teamId).build());
		
		playerRepsitory.save(player);
		
		model.addAttribute("playerList", playerRepsitory.findAll());
		
		return "playerAdd";
	}
	
	// 퇴출선수 등록 페이지
	@GetMapping({"outPlayerAdd"})
	public String outPlayerAdd(Model model) {
		
		List<Player> playerList = new ArrayList<>();
		
		List<Player> playerListEntity = playerRepsitory.findAll();
		
		// 퇴출 안된 선수만 뽑기
		for (Player player : playerListEntity) {
			if(player.getOutPlayer() == null) {
				playerList.add(player);
			}
		}
		
		model.addAttribute("playerList", playerList);
		
		return "outPlayerAdd";
	}
	
	// 선수 등록하기
	@PostMapping({"outPlayer"})
	public String outPlayerAddProc(OutPlayer outPlayer, int playerId, Model model) {
		
		outPlayer.setPlayer(Player.builder().id(playerId).build());
		
		outPlayerRepository.save(outPlayer);
		
		model.addAttribute("playerList", playerRepsitory.findAll());
		
		return "playerList";
	}

	// 야구장 리스트 페이지
	@GetMapping({"", "/", "stadiumList"})
	public String stadiumList(Model model) {
		
		List<Stadium> stadiumListEntity = stadiumRepository.findAllByOrderByName();
		
		List<RankStadiumDto> rankStadiumDtoList = new ArrayList<>();
		
		for (int i = 0; i < stadiumListEntity.size(); i++) {
			RankStadiumDto rankStadiumDto = RankStadiumDto.builder().rank(i+1).stadium(stadiumListEntity.get(i)).build();
			rankStadiumDtoList.add(rankStadiumDto);
		}

		model.addAttribute("rankStadiumDtoList", rankStadiumDtoList);
		
		return "stadiumList";

	}
	
	// 야구장 삭제
	@DeleteMapping({"stadiumList/{stadiumId}"})
	public @ResponseBody List<RankStadiumDto> stadiumDelete(@PathVariable int stadiumId,Model model) {
		
		stadiumRepository.deleteById(stadiumId);
		
		List<Stadium> stadiumListEntity = stadiumRepository.findAllByOrderByName();
		
		List<RankStadiumDto> rankStadiumDtoList = new ArrayList<>();
		
		for (int i = 0; i < stadiumListEntity.size(); i++) {
			
			RankStadiumDto rankStadiumDto = RankStadiumDto.builder().rank(i+1).stadium(stadiumListEntity.get(i)).build();
			rankStadiumDtoList.add(rankStadiumDto);
		}

		return rankStadiumDtoList;

	}
	
	// 팀 리스트 페이지
	@GetMapping({"teamList"})
	public String teamList(Model model) {
		
		List<Team> teamListEntity = teamRepository.findAllByOrderByName();
		
		List<RankTeamDto> rankTeamDtoList = new ArrayList<>();
		
		for (int i = 0; i < teamListEntity.size(); i++) {
			
			RankTeamDto rankTeamDto = RankTeamDto.builder().rank(i+1).team(teamListEntity.get(i)).build();
			rankTeamDtoList.add(rankTeamDto);
		}

		model.addAttribute("rankTeamDtoList", rankTeamDtoList);
		
		return "teamList";
	}
	
	// 팀 삭제
	@DeleteMapping({"teamList/{teamId}"})
	public @ResponseBody List<RankTeamDto> teamDelete(@PathVariable int teamId ,Model model) {
		
		List<Player> playerListEntity = playerRepsitory.findByTeamId(teamId);
		
		for (Player player : playerListEntity) {
			outPlayerRepository.mDeleteByPlayerId(player.getId());
		}

		playerRepsitory.mDeleteByTeamId(teamId);
		teamRepository.mDeleteById(teamId);
		
		List<Team> teamListEntity = teamRepository.findAllByOrderByName();
		
		List<RankTeamDto> rankTeamDtoList = new ArrayList<>();
		
		for (int i = 0; i < teamListEntity.size(); i++) {
			
			RankTeamDto rankTeamDto = RankTeamDto.builder().rank(i+1).team(teamListEntity.get(i)).build();
			rankTeamDtoList.add(rankTeamDto);
		}

		return rankTeamDtoList;
	}
	
	
	// 선수 리스트 페이지
	@GetMapping({"playerList"})
	public String playerList(Model model) {
		
		List<Player> playerListEntity = playerRepsitory.findAllByOrderByName();
		
		List<RankPlayerDto> rankPlayerDtoList = new ArrayList<>();
		
		for (int i = 0; i < playerListEntity.size(); i++) {
			
			RankPlayerDto rankPlayerDto = RankPlayerDto.builder().rank(i+1).player(playerListEntity.get(i)).build();
			rankPlayerDtoList.add(rankPlayerDto);
		}

		model.addAttribute("rankPlayerDtoList", rankPlayerDtoList);
		
		return "playerList";
	}

	// 선수 삭제
	@DeleteMapping({"playerList/{playerId}"})
	public @ResponseBody List<RankPlayerDto> playerDelete(@PathVariable int playerId) {
		
		playerRepsitory.mDeleteById(playerId);
		
		List<Player> playerListEntity = playerRepsitory.findAllByOrderByName();
		
		List<RankPlayerDto> rankPlayerDtoList = new ArrayList<>();
		
		for (int i = 0; i < playerListEntity.size(); i++) {
			
			RankPlayerDto rankPlayerDto = RankPlayerDto.builder().rank(i+1).player(playerListEntity.get(i)).build();
			rankPlayerDtoList.add(rankPlayerDto);
		}

		return rankPlayerDtoList;
	}
	
	// 포지션별 선수 페이지
	@GetMapping({"positionPlayer"})
	public String positionPlayer(Model model) {
		
		List<PositionDto> positionDtoList = playerService.positionDtoList();
		
		model.addAttribute("positionDtoList", positionDtoList);
		
		return "positionPlayer";
	}
	
	// 팀별 선수 페이지
	@GetMapping({"teamPlayer"})
	public String teamPlayer(Model model) {

		model.addAttribute("teamList", teamRepository.findAll());
		
		return "teamPlayer";
	}
	
	// 팀별 선수 가져오기
	@GetMapping({"teamPlayerProc/{teamId}"})
	public @ResponseBody List<Player> teamPlayerProc(@PathVariable int teamId) {
		
		return playerRepsitory.findByTeamId(teamId);
	}

}
