package com.example.leaderboard.models;

import android.net.Uri;

import java.net.URL;

public class LearningLeader {
    private String name;
    private String country;
    private Integer hours;
    private String badgeUrl;
    private String scoreText;



    public LearningLeader(String name, String country, Integer hours, String badgeUrl, String scoreText) {
        this.name = name;
        this.country = country;
        this.hours = hours;
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

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public String getScoreText() {
        return this.hours+" learning hours, "+this.country;
    }

    public void setScoreText(String scoreText) {
        this.scoreText = scoreText;
    }
}
