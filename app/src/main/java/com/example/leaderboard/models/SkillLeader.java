package com.example.leaderboard.models;

public class SkillLeader {
    private String name;
    private String country;
    private Integer score;
    private String badgeUrl;
    private String scoreText;

    public SkillLeader(String name, String country, Integer score, String badgeUrl, String scoreText) {
        this.name = name;
        this.country = country;
        this.score = score;
        this.badgeUrl = badgeUrl;
        this.scoreText = scoreText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public String getScoreText() {
      return this.score+" skill IQ Score, "+this.country;

    }

    public void setScoreText(String scoreText) {
        this.scoreText = scoreText;
    }
}
