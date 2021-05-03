package com.sehgal.ipldashboard;

import com.sehgal.ipldashboard.data.MatchInput;
import com.sehgal.ipldashboard.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {

        Match match =new Match();
        match.setId(Long.valueOf(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        //Set team1 and team2
        String firstInningsTeam, secondInningsTeam;

        if("bat".equals(matchInput.getToss_decision()))
        {
            firstInningsTeam=matchInput.getToss_winner();
            secondInningsTeam=matchInput.getToss_winner().equals(matchInput.getTeam1())?
                    matchInput.getTeam2():matchInput.getTeam1();
        }
        else{
            secondInningsTeam=matchInput.getToss_winner();
            firstInningsTeam=matchInput.getToss_winner().equals(matchInput.getTeam1())?
                    matchInput.getTeam2():matchInput.getTeam1();
            }

        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsTeam);
        match.setTossDecision(matchInput.getToss_decision());
        match.setTossWinner(matchInput.getToss_winner());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());
        return match;
    }


}
