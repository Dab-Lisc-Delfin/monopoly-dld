package com.dld.monopoly.service;

import com.dld.monopoly.model.*;
import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.FieldType;
import com.dld.monopoly.model.game.Game;
import com.dld.monopoly.model.game.GameManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {

    private static Game game;
    private static GameManager gameManager;
    private static GameServiceImpl gameServiceImpl;

    @BeforeAll
    static void initializeGame() {
        gameManager = GameManager.getInstance();
        gameServiceImpl = new GameServiceImpl();
        game = gameServiceImpl.createNewGame();
        gameManager.addGame(game);
    }


    @Test
    void createNewGame_checkGameIdLength_returnTrue() {
        Game game = gameServiceImpl.createNewGame();
        assertEquals(game.getGameId().length(), 5);
    }


    @Test
    void initializeBoard_whenBoardIsNotNull() {
        Board board = gameServiceImpl.initializeBoard();

        assertNotNull(board);
        assertNotNull(board.getFields());
    }

    @Test
    void initializeBoard_shouldAssignCorrectFieldIds() {
        Board board = gameServiceImpl.initializeBoard();

        for (int i = 0; i < 40; i++) {
            assertEquals(board.getFields().get(i).getId(), i + 1);
        }
    }


    @Test
    void findFieldById_whenSearchingPropertyFields_shouldReturnCorrect() {
        Field field = gameServiceImpl.findFieldById(game, 11);
        assertEquals(field.getId(), 11);
        assertEquals(field.getFieldType(), FieldType.JAIL);
        assertEquals(field.getName(), "JAIL");


        field = gameServiceImpl.findFieldById(game, 40);
        assertEquals(field.getId(), 40);
        assertEquals(field.getFieldType(), FieldType.PROPERTY);
        assertEquals(field.getName(), "BOARDWALK");
    }


    @Test
    void findFieldByName_whenSearchingPropertyFields_shouldReturnCorrect() {
        Field field = gameServiceImpl.findFieldByName(game, "JAIL");

        assertEquals(field.getId(), 11);
        assertEquals(field.getFieldType(), FieldType.JAIL);
        assertEquals(field.getName(), "JAIL");


        field = gameServiceImpl.findFieldByName(game, "BOARDWALK");
        assertEquals(field.getId(), 40);
        assertEquals(field.getFieldType(), FieldType.PROPERTY);
        assertEquals(field.getName(), "BOARDWALK");
    }


    @Test
    void addPlayerToGame_whenAddingPlayerToExistingNotStartedGame_shouldReturnCorrect() {
        gameServiceImpl.addPlayerToGame(game.getGameId(), "testNick");
        Player player1 = game.getPlayers().get(0);

        assertEquals(player1.getPlayerIndex(), 1);
        assertEquals(player1.getNickname(), "testNick");
        assertEquals(player1.getPosition().getFieldType(), FieldType.START);
        assertEquals(player1.getMoney(), 1500);
    }

    @Test
    void shuffleCards_shouldShuffleCardsInArray() {
        List<Card> cards = new LinkedList<>();

        Card card1 = new Card("testCard1");
        Card card2 = new Card("testCard2");
        Card card3 = new Card("testCard3");
        Card card4 = new Card("testCard4");
        Card card5 = new Card("testCard5");
        Card card6 = new Card("testCard6");
        Card card7 = new Card("testCard7");

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);
        cards.add(card7);

        List<Card> shuffledDeck = gameServiceImpl.shuffleCards(cards);

        assertNotEquals(shuffledDeck, cards);

        assertTrue(shuffledDeck.contains(card1));
        assertTrue(shuffledDeck.contains(card2));
        assertTrue(shuffledDeck.contains(card3));
        assertTrue(shuffledDeck.contains(card4));
        assertTrue(shuffledDeck.contains(card5));
        assertTrue(shuffledDeck.contains(card6));
        assertTrue(shuffledDeck.contains(card7));

    }
}