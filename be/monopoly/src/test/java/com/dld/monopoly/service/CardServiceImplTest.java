package com.dld.monopoly.service;

import com.dld.monopoly.model.Card;
import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CardServiceImplTest {

    private static CardService cardService;
    private static GameServiceImpl gameService;

    @BeforeAll
    static void initialize() {
        cardService = new CardServiceImpl();
        gameService = new GameServiceImpl();
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


    //TODO HERE WORK
    @Test
    void useCard() {
        Game game = gameService.createNewGame();
        Player testPlayer = gameService.addPlayerToGame(game.getGameId(), "testPlayer");
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 3));
        gameService.addPlayerToGame(game.getGameId(), "additionalPlayer1");
        gameService.addPlayerToGame(game.getGameId(), "additionalPlayer2");

        //card1//
        Card mockCard1 = new Card("Your building loan matures. Collect $150");
        cardService.useCard(testPlayer, mockCard1);
        assertEquals(1650, testPlayer.getMoney()); //1500 + 150 = 1650
        //
        testPlayer.setMoney(1500);


        //card2//
        Card mockCard2 = new Card("You have been elected Chairman of the Board. Pay each player $50.");
        cardService.useCard(testPlayer, mockCard2);
        assertEquals(1400, testPlayer.getMoney()); //1500 - 2*(50) = 1400
        //
        testPlayer.setMoney(1500);


        //card3//
        Card mockCard3 = new Card("Take a trip to Reading Railroad. If you pass Go, collect $200.");
        cardService.useCard(testPlayer, mockCard3);
        assertEquals("Reading Railroad", testPlayer.getPosition().getName());
        assertEquals(1500, testPlayer.getMoney()); // shouldn't get extra money


        testPlayer.setCurrentPosition(gameService.findFieldById(game, 18));
        //
        cardService.useCard(testPlayer, mockCard3);
        assertEquals("Reading Railroad", testPlayer.getPosition().getName());
        assertEquals(1700, testPlayer.getMoney()); // should get extra money
        //
        testPlayer.setMoney(1500);


        testPlayer.setCurrentPosition(gameService.findFieldById(game, 34));
        //
        cardService.useCard(testPlayer, mockCard3);
        assertEquals("Reading Railroad", testPlayer.getPosition().getName());
        assertEquals(1700, testPlayer.getMoney()); // should get extra money
        //
        testPlayer.setMoney(1500);


        Card mockCard4 = new Card("Speeding fine $15.");

    }


    @Test
    void initializeChanceCards() {
        List<Card> chanceCards = cardService.initializeChanceCards();

        assertEquals("Advance to Boardwalk", chanceCards.get(0).getName());
        assertEquals("Get Out of Jail Free.", chanceCards.get(8).getName());
        assertEquals("Your building loan matures. Collect $150", chanceCards.get(chanceCards.size() - 1).getName());

        assertTrue(chanceCards.size() == 16);
    }

    @Test
    void initializeCommunityChestCards() {
        List<Card> communityChestCards = cardService.initializeCommunityChestCards();

        assertEquals("Advance to Go (Collect $200)", communityChestCards.get(0).getName());
        assertEquals("It is your birthday. Collect $10 from every player", communityChestCards.get(8).getName());
        assertEquals("You inherit $100", communityChestCards.get(communityChestCards.size() - 1).getName());

        assertTrue(communityChestCards.size() == 16);
    }
}