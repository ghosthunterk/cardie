package com.example.cardie;

public class Card {
    private String CardId;
    private String CardWord;
    private String CardDefinition;
    private int CardImageUrl;
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
    public Card(String cardId, String cardWord, String cardType, String cardDefinition, int cardImageUrl, String cardTranslation, String cardAudio) {
        CardId = cardId;
        CardWord = cardWord;
        CardDefinition = cardDefinition;
        CardImageUrl = cardImageUrl;
        CardTranslation = cardTranslation;
        CardAudio = cardAudio;
        CardType = cardType;
    }
    public Card(String cardId, String cardWord, String cardType, int cardImageUrl)
    {
        CardId = cardId;
        CardWord = cardWord;
        CardType = cardType;
        CardImageUrl = cardImageUrl;
    }
    public Card(String cardId, String cardWord,String cardDefinition, String cardType, int cardImageUrl)
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

    public int getCardImageUrl() {
        return CardImageUrl;
    }

    public void setCardImageUrl(int cardImageUrl) {
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
