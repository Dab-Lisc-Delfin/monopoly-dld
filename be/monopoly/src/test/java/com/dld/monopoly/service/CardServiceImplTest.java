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
    void getChanceCard() {
        List<Card> chanceCards = cardService.initializeChanceCards();
        Card card = cardService.getChanceCard(chanceCards);

        //get first card from the top
        assertEquals("Your building loan matures. Collect $150", card.getName());

        //check if that card went to the end of the deck
        assertEquals("Your building loan matures. Collect $150", chanceCards.get(0).getName());
        assertNotEquals("Your building loan matures. Collect $150", chanceCards.get(chanceCards.size() - 1).getName());
    }


    @Test
    void getCommunityChestCard() {
        //todo
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