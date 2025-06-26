package com.dld.monopoly.service;

import com.dld.monopoly.model.game.Game;
import com.dld.monopoly.model.game.GameManager;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.FieldColor;
import com.dld.monopoly.model.fields.RentableProperty;
import com.dld.monopoly.model.fields.ResidentialProperty;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceImplTest {
    private static Game game;
    private static GameManager gameManager;
    private static GameServiceImpl gameServiceImpl;
    private static TransactionServiceImpl transactionServiceImpl;

    @BeforeAll
    static void initializeGame() {
        gameManager = new GameManager();
        gameServiceImpl = new GameServiceImpl();
        transactionServiceImpl = new TransactionServiceImpl(gameServiceImpl);

        game = gameServiceImpl.createNewGame();
        gameManager.addGame(game);
    }


    @Test
    void buyProperty_whenPlayerCanBuyProperty_returnCorrect() {
        Player player = new Player();
        player.setAfterRoll(true);
        RentableProperty field = (RentableProperty) gameServiceImpl.findFieldById(game, 4);
        player.setPosition(field);
        transactionServiceImpl.buyProperty(game, player);

        assertEquals(1440, player.getMoney());
        assertEquals(1, player.getProperties().size());
        assertEquals(player, field.getOwner());
    }


    @Test
    void payPropertyOwner_buyResidentialProperty_when1Hotel() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setMoney(1000);
        player2.setMoney(1000);

        ResidentialProperty residentialProperty = new ResidentialProperty(2, "testProperty", FieldColor.BROWN, 100, 50, 100, 200, 300, 400, 500, 1000, 100, 100);

        player2.setCurrentPosition(residentialProperty);
        residentialProperty.setHotelsAmount(1);
        residentialProperty.setOwner(player1);

        transactionServiceImpl.payPropertyOwner(player1, player2);
        assertEquals(2000, player1.getMoney());
        assertEquals(0, player2.getMoney());
    }


    @Test
    void payPropertyOwner_buyResidentialProperty_when4Houses() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setMoney(1000);
        player2.setMoney(1000);

        ResidentialProperty residentialProperty = new ResidentialProperty(2, "testProperty", FieldColor.BROWN, 100, 50, 100, 200, 300, 400, 500, 1000, 100, 100);

        player2.setCurrentPosition(residentialProperty);
        residentialProperty.setHousesAmount(4);
        residentialProperty.setOwner(player1);

        transactionServiceImpl.payPropertyOwner(player1, player2);
        assertEquals(1500, player1.getMoney());
        assertEquals(500, player2.getMoney());
    }


    @Test
    void payPropertyOwnery_buyResidentialProperty_when3Houses() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setMoney(1000);
        player2.setMoney(1000);

        ResidentialProperty residentialProperty = new ResidentialProperty(2, "testProperty", FieldColor.BROWN, 100, 50, 100, 200, 300, 400, 500, 1000, 100, 100);

        player2.setCurrentPosition(residentialProperty);
        residentialProperty.setHousesAmount(3);
        residentialProperty.setOwner(player1);

        transactionServiceImpl.payPropertyOwner(player1, player2);
        assertEquals(1400, player1.getMoney());
        assertEquals(600, player2.getMoney());
    }


    @Test
    void payPropertyOwner_buyResidentialProperty_when2Houses() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setMoney(1000);
        player2.setMoney(1000);

        ResidentialProperty residentialProperty = new ResidentialProperty(2, "testProperty", FieldColor.BROWN, 100, 50, 100, 200, 300, 400, 500, 1000, 100, 100);

        player2.setCurrentPosition(residentialProperty);
        residentialProperty.setHousesAmount(2);
        residentialProperty.setOwner(player1);

        transactionServiceImpl.payPropertyOwner(player1, player2);
        assertEquals(1300, player1.getMoney());
        assertEquals(700, player2.getMoney());
    }


    @Test
    void payPropertyOwner_buyResidentialProperty_when1House() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setMoney(1000);
        player2.setMoney(1000);

        ResidentialProperty residentialProperty = new ResidentialProperty(2, "testProperty", FieldColor.BROWN, 100, 50, 100, 200, 300, 400, 500, 1000, 100, 100);

        player2.setCurrentPosition(residentialProperty);
        residentialProperty.setHousesAmount(1);
        residentialProperty.setOwner(player1);

        transactionServiceImpl.payPropertyOwner(player1, player2);
        assertEquals(1200, player1.getMoney());
        assertEquals(800, player2.getMoney());
    }


    @Test
    void payPropertyOwner_buyResidentialProperty_whenColorSet() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setMoney(1000);
        player2.setMoney(1000);


        ResidentialProperty residentialProperty1 = new ResidentialProperty(2, "testProperty", FieldColor.BROWN, 100, 50, 100, 200, 300, 400, 500, 1000, 100, 100);
        ResidentialProperty residentialProperty2 = new ResidentialProperty(3, "testProperty2", FieldColor.BROWN, 100, 50, 100, 200, 300, 400, 500, 1000, 100, 100);
        residentialProperty1.setOwner(player1);

        player1.setProperties(List.of(residentialProperty1, residentialProperty2));
        player2.setCurrentPosition(residentialProperty1);

        transactionServiceImpl.payPropertyOwner(player1, player2);
        assertEquals(1100, player1.getMoney());
        assertEquals(900, player2.getMoney());
    }


    @Test
    void payPropertyOwner_buyResidentialProperty_whenJustOwnProperty() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setMoney(1000);
        player2.setMoney(1000);


        ResidentialProperty residentialProperty1 = new ResidentialProperty(2, "testProperty", FieldColor.BROWN, 100, 50, 100, 200, 300, 400, 500, 1000, 100, 100);
        residentialProperty1.setOwner(player1);
        player2.setCurrentPosition(residentialProperty1);

        transactionServiceImpl.payPropertyOwner(player1, player2);
        assertEquals(1050, player1.getMoney());
        assertEquals(950, player2.getMoney());
    }
}