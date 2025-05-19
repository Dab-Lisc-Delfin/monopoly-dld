package com.dld.monopoly.service;

import com.dld.monopoly.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
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
        List<Card> testDeck = new ArrayList<>();
        testDeck.add(new Card("1"));
        testDeck.add(new Card("2"));
        testDeck.add(new Card("3"));
        testDeck.add(new Card("4"));
        testDeck.add(new Card("5"));

        log.info("Deck before shuffle: {} {} {} {} {}", testDeck.get(0).getName(), testDeck.get(1).getName(), testDeck.get(2).getName(), testDeck.get(3).getName(), testDeck.get(4).getName());

        cardService.shuffleDeck(testDeck);

        log.info("Deck after shuffle {} {} {} {} {}", testDeck.get(0).getName(), testDeck.get(1).getName(), testDeck.get(2).getName(), testDeck.get(3).getName(), testDeck.get(4).getName());
    }


    @Test
    void useCard() {
        //todo
    }


    @Test
    void initializeChanceCards() {
        List<Card> chanceCards = cardService.initializeChanceCards();

        assertEquals("Advance to Boardwalk", chanceCards.get(0).getName());
        assertEquals("Get Out of Jail Free.", chanceCards.get(8).getName());
        assertEquals("Your building loan matures. Collect $150", chanceCards.get(chanceCards.size()-1).getName());

        assertTrue(chanceCards.size() == 16);
    }

    @Test
    void initializeCommunityChestCards() {
        List<Card> communityChestCards = cardService.initializeCommunityChestCards();

        assertEquals("Advance to Go (Collect $200)", communityChestCards.get(0).getName());
        assertEquals("It is your birthday. Collect $10 from every player", communityChestCards.get(8).getName());
        assertEquals("You inherit $100", communityChestCards.get(communityChestCards.size()-1).getName());

        assertTrue(communityChestCards.size() == 16);
    }
}