package com.example.cardie.Models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Card {
    @SerializedName("_id")
    private String CardId;
    @SerializedName("Name")
    private String CardWord;
    @SerializedName("Definition")
    private String CardDefinition;
    @SerializedName("Img")
    private String CardImageUrl;
    private String CardTranslation;
    private String CardAudio;
    private String CardType;

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public Card()
    {}
    public Card(String cardId, String cardWord, String cardType, String cardDefinition, String cardImageUrl, String cardTranslation, String cardAudio) {
        CardId = cardId;
        CardWord = cardWord;
        CardDefinition = cardDefinition;
        CardImageUrl = cardImageUrl;
        CardTranslation = cardTranslation;
        CardAudio = cardAudio;
        CardType = cardType;
    }
    public Card(@Nullable  String cardId, String cardWord, String cardType, String cardImageUrl)
    {
        CardId = cardId;
        CardWord = cardWord;
        CardType = cardType;
        CardImageUrl = cardImageUrl;
    }

    public Card(@Nullable String cardId, String cardWord, String cardDefinition, String cardType, String cardImageUrl)
    {
        CardId = cardId;
        CardWord = cardWord;
        CardDefinition=cardDefinition;
        CardType = cardType;
        CardImageUrl = cardImageUrl;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    public String getCardWord() {
        return CardWord;
    }

    public void setCardWord(String cardWord) {
        CardWord = cardWord;
    }

    public String getCardDefinition() {
        return CardDefinition;
    }

    public void setCardDefinition(String cardDefinition) {
        CardDefinition = cardDefinition;
    }

    public String getCardImageUrl() {
        return CardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        CardImageUrl = cardImageUrl;
    }

    public String getCardTranslation() {
        return CardTranslation;
    }

    public void setCardTranslation(String cardTranslation) {
        CardTranslation = cardTranslation;
    }

    public String getCardAudio() {
        return CardAudio;
    }

    public void setCardAudio(String cardAudio) {
        CardAudio = cardAudio;
    }
}
