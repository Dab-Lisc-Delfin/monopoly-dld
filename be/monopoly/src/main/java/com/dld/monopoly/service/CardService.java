package com.dld.monopoly.service;

import com.dld.monopoly.model.Card;

import java.util.List;

public interface CardService {

    List<Card> initializeChanceCards();

    List<Card> initializeCommunityChestCards();

    Card getChanceCard();

    Card getCommunityChestCard();

    List<Card> shuffleDeck();

    void useCard(Card card);

}
