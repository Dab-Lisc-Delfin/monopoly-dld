package com.dld.monopoly.service;

import com.dld.monopoly.model.Card;
import com.dld.monopoly.model.game.Game;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.FieldType;
import com.dld.monopoly.model.fields.RentableProperty;
import com.dld.monopoly.model.fields.ResidentialProperty;
import com.dld.monopoly.model.fields.builder.ResidentalPropertyBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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



    @Disabled
    @Test
    void useCard_chanceCards() {
        Game game = gameService.createNewGame();
        ResidentalPropertyBuilder propertyBuilder = new ResidentalPropertyBuilder();
        Player testPlayer = gameService.addPlayerToGame(game.getGameId(), "testPlayer"); //1 - MAIN
        Player testPlayer2 = gameService.addPlayerToGame(game.getGameId(), "testPlayer2"); //2
        gameService.addPlayerToGame(game.getGameId(), "additionalPlayer1"); //3
        gameService.addPlayerToGame(game.getGameId(), "additionalPlayer2"); //4
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 8));

        //card1//
        Card mockCard1 = new Card("Your building loan matures. Collect $150");
        cardService.useCard(testPlayer, mockCard1);
        assertEquals(1650, testPlayer.getMoney()); //1500 + 150 = 1650
        //
        testPlayer.setMoney(1500);


        //card2//
        Card mockCard2 = new Card("You have been elected Chairman of the Board. Pay each player $50.");
        cardService.useCard(testPlayer, mockCard2);
        assertEquals(1350, testPlayer.getMoney()); //1500 - 3*(50) = 1350
        //
        testPlayer.setMoney(1500);


        //card3// 1
        Card mockCard3 = new Card("Take a trip to Reading Railroad. If you pass Go, collect $200.");
        cardService.useCard(testPlayer, mockCard3);
        assertEquals("Reading Railroad", testPlayer.getPosition().getName());
        assertEquals(1700, testPlayer.getMoney()); // should get extra money
        //||
        //|| 2
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 23));
        testPlayer.setMoney(1500);
        //
        cardService.useCard(testPlayer, mockCard3);
        assertEquals("Reading Railroad", testPlayer.getPosition().getName());
        assertEquals(1700, testPlayer.getMoney()); // should get extra money
        //
        testPlayer.setMoney(1500);
        //||
        //|| 3
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 37));
        //
        cardService.useCard(testPlayer, mockCard3);
        assertEquals("Reading Railroad", testPlayer.getPosition().getName());
        assertEquals(1700, testPlayer.getMoney()); // should get extra money
        //
        testPlayer.setMoney(1500);
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 8));


        Card mockCard4 = new Card("Speeding fine $15.");
        cardService.useCard(testPlayer, mockCard4);
        assertEquals(1485, testPlayer.getMoney()); //1500 - 15 = 1485
        //
        testPlayer.setMoney(1500);


        ResidentialProperty mockProperty1 = propertyBuilder.createObject();
        ResidentialProperty mockProperty2 = propertyBuilder.createObject();
        ResidentialProperty mockProperty3 = propertyBuilder.createObject();
        mockProperty1.setHousesAmount(4);
        mockProperty2.setHousesAmount(2);
        mockProperty3.setHotelsAmount(1);
        List<RentableProperty> properties = List.of(mockProperty1, mockProperty2, mockProperty3);
        testPlayer.setProperties(properties);
        //
        Card mockCard5 = new Card("Make general repairs on all your property. For each house pay $25. For each hotel pay $100.");
        cardService.useCard(testPlayer, mockCard5); // 6*25 +100 = 250
        assertEquals(1350, testPlayer.getMoney()); //1500 - 250 = 1350
        //
        testPlayer.setMoney(1500);


        Card mockCard6 = new Card("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200.");
        cardService.useCard(testPlayer, mockCard6);
        assertEquals(1500, testPlayer.getMoney());
        assertEquals(FieldType.JAIL, testPlayer.getCurrentPosition().getFieldType());
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 8));


        Card mockCard7 = new Card("Go Back 3 Spaces.");
        cardService.useCard(testPlayer, mockCard7);
        assertEquals(5, testPlayer.getCurrentPosition().getId()); // id:8 -> id:5
        assertEquals(1300,testPlayer.getMoney()); //INCOME TAX
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 23));
        assertEquals(5, testPlayer.getCurrentPosition().getId()); // id:23 -> id:20
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 37));
        assertEquals(5, testPlayer.getCurrentPosition().getId()); // id:37 -> id:34
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 8));



        Card mockCard8 = new Card("Get Out of Jail Free."); //TODO
        cardService.useCard(testPlayer, mockCard8);


        Card mockCard9 = new Card("Bank pays you dividend of $50.");
        cardService.useCard(testPlayer, mockCard9);
        assertEquals(1550, testPlayer.getMoney()); //1500 + 50 = 1550
        //
        testPlayer.setMoney(1500);


        Card mockCard10 = new Card("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown."); //TODO
        cardService.useCard(testPlayer, mockCard10);
        assertEquals("ELECTRIC COMPANY", testPlayer.getCurrentPosition().getName());
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 23));
        cardService.useCard(testPlayer, mockCard10);
        assertEquals("WATER WORKS", testPlayer.getCurrentPosition().getName());
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 37));
        cardService.useCard(testPlayer, mockCard10);
        assertEquals("ELECTRIC COMPANY", testPlayer.getCurrentPosition().getName());
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 8));


        Card mockCard11 = new Card("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay wonder twice the rental to which they are otherwise entitled");
        cardService.useCard(testPlayer, mockCard11);
        assertEquals("READING RAILROAD", testPlayer.getCurrentPosition().getName());
        assertEquals(1500, testPlayer.getMoney());
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 23));
        cardService.useCard(testPlayer, mockCard11);
        assertEquals("B&O RAILROAD", testPlayer.getCurrentPosition());
        assertEquals(1500, testPlayer.getMoney());
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 37));
        List<RentableProperty> propertiesPlayer2 = new ArrayList<>();
        propertiesPlayer2.add((RentableProperty) gameService.findFieldByName(game, "READING RAILROAD"));
        propertiesPlayer2.add((RentableProperty) gameService.findFieldByName(game, "SHORT LINE RAILROAD"));
        testPlayer2.setProperties(propertiesPlayer2);
        cardService.useCard(testPlayer, mockCard11);
        assertEquals("READING RAILROAD", testPlayer.getCurrentPosition().getName());
        assertEquals(1400, testPlayer.getMoney()); // 1500 - 50*2 = 1400
        //
        testPlayer.setMoney(1500);
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 8));


        Card mockCard12 = new Card("Advance to St. Charles Place. If you pass Go, collect $200");
        cardService.useCard(testPlayer, mockCard12);
        assertEquals("ST. CHARLES PLACE", testPlayer.getCurrentPosition());
        assertEquals(1500, testPlayer.getMoney());
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 23));
        cardService.useCard(testPlayer, mockCard12);
        assertEquals("ST. CHARLES PLACE", testPlayer.getCurrentPosition());
        assertEquals(1700, testPlayer.getMoney()); //"If you pass Go, collect $200"
        //
        testPlayer.setMoney(1500);
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 37));
        cardService.useCard(testPlayer, mockCard12);
        assertEquals("ST. CHARLES PLACE", testPlayer.getCurrentPosition());
        assertEquals(1700, testPlayer.getMoney()); //"If you pass Go, collect $200"
        //
        testPlayer.setMoney(1500);
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 8));


        Card mockCard13 = new Card("Advance to Illinois Avenue. If you pass Go, collect $200");
        cardService.useCard(testPlayer, mockCard13);
        assertEquals("ILLINOIS AVENUE", testPlayer.getCurrentPosition());
        assertEquals(1500, testPlayer.getMoney());
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 23));
        cardService.useCard(testPlayer, mockCard13);
        assertEquals("ILLINOIS AVENUE", testPlayer.getCurrentPosition());
        assertEquals(1500, testPlayer.getMoney());
        //
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 37));
        cardService.useCard(testPlayer, mockCard13);
        assertEquals("ILLINOIS AVENUE", testPlayer.getCurrentPosition());
        assertEquals(1700, testPlayer.getMoney()); //"If you pass Go, collect $200"
        //
        testPlayer.setMoney(1500);
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 8));


        Card mockCard14 = new Card("Advance to Go (Collect $200)");
        cardService.useCard(testPlayer, mockCard14);
        assertEquals("START", testPlayer.getCurrentPosition().getName());
        assertEquals(1700, testPlayer.getMoney());
        //
        testPlayer.setMoney(1500);
        testPlayer.setCurrentPosition(gameService.findFieldById(game, 3));


        Card mockCard15 = new Card("Advance to Boardwalk");
        cardService.useCard(testPlayer, mockCard15);
        assertEquals("BOARDWALK", testPlayer.getCurrentPosition().getName());
        assertEquals(1500, testPlayer.getMoney());
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