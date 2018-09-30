package com.betconstruct.demo.model;


import com.betconstruct.demo.model.Item;

public class Child implements Item {
    private int id;

    private int groupId;

//    @JsonProperty("matchDate")
    private String matchDate;

//    @JsonProperty("homeTeamResult")
    private int homeTeamResult;

//    @JsonProperty("awayTeamResult")
    private int awayTeamResult;

//    @JsonProperty("awayTeamName")
    private String awayTeamName;

//    @JsonProperty("homeTeamAbbreviation")
    private String homeTeamAbbreviation;

//    @JsonProperty("homeTeamName")
    private String homeTeamName;

//    @JsonProperty("homeTeamId")
    private int homeTeamId;

//    @JsonProperty("awayTeamAbbreviation")
    private String awayTeamAbbreviation;

//    @JsonProperty("awayTeamId")
    private int awayTeamId;

//    @JsonProperty("matchId")
    private int matchId;

//    @JsonProperty("status")
    private int status;

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setHomeTeamResult(int homeTeamResult) {
        this.homeTeamResult = homeTeamResult;
    }

    public int getHomeTeamResult() {
        return homeTeamResult;
    }

    public void setAwayTeamResult(int awayTeamResult) {
        this.awayTeamResult = awayTeamResult;
    }

    public int getAwayTeamResult() {
        return awayTeamResult;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setHomeTeamAbbreviation(String homeTeamAbbreviation) {
        this.homeTeamAbbreviation = homeTeamAbbreviation;
    }

    public String getHomeTeamAbbreviation() {
        return homeTeamAbbreviation;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public void setAwayTeamAbbreviation(String awayTeamAbbreviation) {
        this.awayTeamAbbreviation = awayTeamAbbreviation;
    }

    public String getAwayTeamAbbreviation() {
        return awayTeamAbbreviation;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "FixtureMatchesItem{" +
                        "matchDate = '" + matchDate + '\'' +
                        ",homeTeamResult = '" + homeTeamResult + '\'' +
                        ",awayTeamResult = '" + awayTeamResult + '\'' +
                        ",awayTeamName = '" + awayTeamName + '\'' +
                        ",homeTeamAbbreviation = '" + homeTeamAbbreviation + '\'' +
                        ",homeTeamName = '" + homeTeamName + '\'' +
                        ",homeTeamId = '" + homeTeamId + '\'' +
                        ",awayTeamAbbreviation = '" + awayTeamAbbreviation + '\'' +
                        ",awayTeamId = '" + awayTeamId + '\'' +
                        ",matchId = '" + matchId + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    @Override
    public boolean isGroup() {
        return false;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getGroupId() {
        return groupId;
    }
}