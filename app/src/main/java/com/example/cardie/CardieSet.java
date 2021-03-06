package com.example.cardie;

import androidx.annotation.Nullable;

public class CardieSet {
    private int Difficulty;
    private String SetId;
    private String SetName;
    private int SetQuantity;
    private String UserId;
    private int SetImgUrl;

    public int getSetImgUrl() {
        return SetImgUrl;
    }

    public void setSetImgUrl(int setImgUrl) {
        SetImgUrl = setImgUrl;
    }

    public CardieSet(String setId, String setName, @Nullable int setImgUrl, int setQuantity, @Nullable String userId, int difficulty) {
        SetId = setId;
        SetName = setName;
        SetQuantity = setQuantity;
        UserId = userId;
        SetImgUrl = setImgUrl;
        Difficulty = difficulty;

    }

    public int getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.Difficulty = difficulty;
    }



    public String getSetId() {
        return SetId;
    }

    public void setSetId(String setId) {
        SetId = setId;
    }

    public String getSetName() {
        return SetName;
    }

    public void setSetName(String setName) {
        SetName = setName;
    }

    public int getSetQuantity() {
        return SetQuantity;
    }

    public void setSetQuantity(int setQuantity) {
        SetQuantity = setQuantity;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
