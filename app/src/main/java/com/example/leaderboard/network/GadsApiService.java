package com.example.leaderboard.network;

import com.example.leaderboard.models.LearningLeader;
import com.example.leaderboard.models.SkillLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GadsApiService {
    @GET("/api/hours")
    Call<List<LearningLeader>> getLearningLeaders();

    @GET("/api/skilliq")
    Call<List<SkillLeader>> getSkillLeaders();
}
