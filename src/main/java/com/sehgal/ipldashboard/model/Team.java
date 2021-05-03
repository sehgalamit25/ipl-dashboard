package com.sehgal.ipldashboard.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Team {
    @Id
    private long id;
    private String teamName;
    private long totalMatches;
    private long toalWins;
}
