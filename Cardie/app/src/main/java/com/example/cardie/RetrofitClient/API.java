package com.example.cardie.RetrofitClient;

import com.example.cardie.Models.Card;
import com.example.cardie.Models.CardieSet;
import com.example.cardie.Models.Card;
import com.example.cardie.Models.CardieSet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    String BASE_URL = "";
    String LOCAL_URL = "http://192.168.0.108:3000/";
    String LOCAL_URL2 ="http://192.168.1.77:3000/";

    @GET("/all-card")
    Call<List<Card>> getCard();

//    @POST("getData/card")
//    Call<List<Card>> getTrans(@Body Card category);
    @GET("/all-set")
    Call<List<CardieSet>> getAllSet();
    @GET("/set")
    Call<CardieSet> getSet(@Query("SetName") String SetName);

    @GET("/set-cards")
    Call<List<Card>> getCardsBySet(@Query("SetName") String SetName);

//    @GET("getData/history?")
//    Call<List<UserHistory>> getUserHistory(@Query("email") String email);

//    @POST("getData/saveResult")
////    void saveTest(@Query("email") String email,@Query("Cat") String Cat, @Query("Day") String Day, @Query("Score") int Score);
//    Call<TestResultDetail> saveTest(@Body TestResultDetail TestResult);
}
