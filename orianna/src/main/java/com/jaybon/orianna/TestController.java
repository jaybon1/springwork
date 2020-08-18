package com.jaybon.orianna;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

@RestController
public class TestController {
	
	@GetMapping({"","/"})
	public String index() {
        Orianna.setRiotAPIKey("RGAPI-8f2ab161-b201-4d25-a846-17abf656e8e7");
        Orianna.setDefaultRegion(Region.KOREA);

        Summoner summoner = Orianna.summonerNamed("원모타임").get();
        System.out.println(summoner.getLevel());
		return "1";
	}

}
