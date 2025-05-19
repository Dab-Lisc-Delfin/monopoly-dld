package com.dld.monopoly.service;

import com.dld.monopoly.model.Card;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardServiceImplTest {

    private static CardService cardService;

    @BeforeAll
    static void initialize() {
        cardService = new CardServiceImpl();
    }


    @Test
    void getCardFromDeck_whenGivenChanceCards() {
        List<Card> chanceCards = cardService.initializeChanceCards();
        Card card = cardService.getCardFromDeck(chanceCards);

        //get first card from the top
        assertEquals("Your building loan matures. Collect $150", card.getName());

        //check if that card went to the end of the deck
        assertEquals("Your building loan matures. Collect $150", chanceCards.get(0).getName());
        assertNotEquals("Your building loan matures. Collect $150", chanceCards.get(chanceCards.size() - 1).getName());
    }


    @Test
    void getCardFromDeck_whenGivenCommunityChestCards() {
        List<Card> communityCards = cardService.initializeCommunityChestCards();
        Card card = cardService.getCardFromDeck(communityCards);

        //get first card from the top
        assertEquals("You inherit $100", card.getName());

        //check if that card went to the end of the deck
        assertEquals("You inherit $100", communityCards.get(0).getName());
        assertNotEquals("You inherit $100", communityCards.get(communityCards.size() - 1).getName());
    }


    @Test
    void shuffleDeck() {
        //todo

    }

    @Test
    void useCard() {
        //todo
    }

    @Test
    void initializeChanceCards() {
        //todo
    }

    @Test
    void initializeCommunityChestCards() {
        //todo
    }
}