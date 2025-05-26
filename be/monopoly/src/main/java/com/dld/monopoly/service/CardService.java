package com.dld.monopoly.service;

import com.dld.monopoly.model.Card;
import com.dld.monopoly.model.Player;

import java.util.List;

public interface CardService {

    List<Card> initializeChanceCards();

    List<Card> initializeCommunityChestCards();

    Card getCardFromDeck(List<Card> deck);

    void shuffleDeck(List<Card> deck);

    void useCard(Player player, Card card);

}
