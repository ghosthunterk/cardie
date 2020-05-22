package com.example.cardie;

import com.example.cardie.Models.CardieSet;

public interface SetListener {
    void onSetClick(CardieSet set);
    void onPracticeButtonClick(String setName);
    void onSetClick(String setName);
}
