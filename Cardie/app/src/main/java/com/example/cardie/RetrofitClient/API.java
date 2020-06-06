package com.example.cardie.RetrofitClient;

import com.example.cardie.Models.Card;
import com.example.cardie.Models.CardieSet;
import com.example.cardie.Models.Card;
import com.example.cardie.Models.CardieSet;
import com.example.cardie.Models.User;
import com.example.cardie.UserProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    String BASE_URL = "";
    String LOCAL_URL = "http://192.168.1.9:3000/";
    String LOCAL_URL2 ="http://192.168.1.77:3000/";
    String LOCAL_URL3 ="http://192.168.43.125:3000/";
    @GET("/all-card")
    Call<List<Card>> getCard();

//    @POST("getData/card")
//    Call<List<Card>> getTrans(@Body Card category);
    @GET("/all-set")
    Call<List<CardieSet>> getAllSet();
    @GET("/set")
    Call<CardieSet> getSet(@Query("SetName") String SetName);

    @GET("/card-by-set")
    Call<List<Card>> getCardsBySet(@Query("SetName") String SetName);

    @GET("/user")
    Call<User> getUserProfile(@Query("Username") String username);

    @FormUrlEncoded
    @POST("/add-user")
    Call<String> addUser(@Field("user") String username);

//    @GET("getData/history?")
//    Call<List<UserHistory>> getUserHistory(@Query("email") String email);

    @POST("/add-card")
    Call<Card> addCard(@Body Card newCard);

    @FormUrlEncoded
    @POST("/add-card")
    Call<Card> addCard(@Field("Name") String CardName, @Field("Definition") String CardDefinition,@Field("Img") String cardImg, @Field("Type") String CardType);



}
