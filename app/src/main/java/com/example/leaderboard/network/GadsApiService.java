package com.example.leaderboard.network;

import com.example.leaderboard.models.LearningLeader;
import com.example.leaderboard.models.SkillLeader;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GadsApiService {
    @GET("/api/hours")
    Call<List<LearningLeader>> getLearningLeaders();

    @GET("/api/skilliq")
    Call<List<SkillLeader>> getSkillLeaders();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Void> submitProject(@Field("entry.1824927963")
                                       String email,
                               @Field("entry.1877115667")
                                       String firstName,
                               @Field("entry.2006916086")
                                       String lastName,
                               @Field("entry.284483984")
                                       String link);
}
