package com.dld.monopoly.service;

import com.dld.monopoly.model.Board;
import com.dld.monopoly.model.Card;
import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.fields.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class GameManagerServiceImpl implements GameManagerService {
    private GameManager gameManager;

    public GameManagerServiceImpl(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public Game createNewGame() {
        String randomId = UUID.randomUUID().toString().substring(0, 5);
        Game game = new Game(randomId, initializeBoard());
        game.setPlayers(new ArrayList<>());
        gameManager.addGame(game);
        System.out.println("GAMEEE ID " + game.getGameId());
        return game;
    }


    protected Game getGameById(String gameId) {
        List<Game> games = gameManager.getActiveGames();

        for (Game game : games) {
            if (game.getGameId().equals(gameId)) {
                return game;
            }
        }

        return null; //todo
    }


    protected Board initializeBoard() {
        List<Field> boardField = new ArrayList<>();

        boardField.add(new Field(1, "START", FieldType.START));

        boardField.add(new ResidentialProperty(2, "MEDITERRANEAN AVENUE", FieldColor.BROWN, 60, 2, 4, 10, 30, 90, 160, 250, 50, 50));
        boardField.add(new Field(3, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));
        boardField.add(new ResidentialProperty(4, "BALTIC AVENUE", FieldColor.BROWN, 60, 4, 8, 20, 60, 180, 320, 450, 50, 50));
        boardField.add(new Field(5, "INCOME TAX", FieldType.TAX));
        boardField.add(new RailroadProperty(6, "READING RAILROAD"));
        boardField.add(new ResidentialProperty(7, "ORIENTAL AVENUE", FieldColor.LIGHT_BLUE, 100, 6, 12, 30, 90, 270, 400, 550, 50, 50));
        boardField.add(new Field(8, "CHANCE", FieldType.CHANCE));
        boardField.add(new ResidentialProperty(9, "VERMONT AVENUE", FieldColor.LIGHT_BLUE, 100, 6, 12, 30, 90, 270, 400, 550, 50, 50));
        boardField.add(new ResidentialProperty(10, "CONNECTICUT AVENUE", FieldColor.LIGHT_BLUE, 120, 8, 16, 40, 100, 300, 450, 600, 50, 50));
        boardField.add(new Field(11, "JAIL", FieldType.JAIL));

        boardField.add(new ResidentialProperty(12, "ST. CHARLES PLACE", FieldColor.PINK, 140, 10, 20, 50, 150, 450, 625, 750, 100, 100));
        boardField.add(new ElectricProperty(13, "ELECTRIC COMPANY"));
        boardField.add(new ResidentialProperty(14, "STATES AVENUE", FieldColor.PINK, 140, 10, 20, 50, 150, 450, 625, 750, 100, 100));
        boardField.add(new ResidentialProperty(15, "VIRGINIA AVENUE", FieldColor.PINK, 160, 12, 24, 60, 180, 500, 700, 900, 100, 100));
        boardField.add(new RailroadProperty(16, "PENNSYLVANIA RAILROAD"));
        boardField.add(new ResidentialProperty(17, "ST. JAMES PLACE", FieldColor.ORANGE, 180, 14, 28, 70, 200, 550, 750, 950, 100, 100));
        boardField.add(new Field(18, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));
        boardField.add(new ResidentialProperty(19, "TENNESSEE AVENUE", FieldColor.ORANGE, 180, 14, 28, 70, 200, 550, 750, 950, 100, 100));
        boardField.add(new ResidentialProperty(20, "NEW YORK AVENUE", FieldColor.ORANGE, 200, 16, 32, 80, 220, 600, 800, 1000, 100, 100));
        boardField.add(new Field(21, "FREE PARKING", FieldType.FREE_PARKING));

        boardField.add(new ResidentialProperty(22, "KENTUCKY AVENUE", FieldColor.RED, 220, 18, 36, 90, 250, 700, 875, 1050, 150, 150));
        boardField.add(new Field(23, "CHANCE", FieldType.CHANCE));
        boardField.add(new ResidentialProperty(24, "INDIANA AVENUE", FieldColor.RED, 220, 18, 36, 90, 250, 700, 875, 1050, 150, 150));
        boardField.add(new ResidentialProperty(25, "ILLINOIS AVENUE", FieldColor.RED, 240, 20, 40, 100, 300, 750, 925, 1100, 150, 150));
        boardField.add(new RailroadProperty(26, "B&O RAILROAD"));
        boardField.add(new ResidentialProperty(27, "ATLANTIC AVENUE", FieldColor.YELLOW, 260, 22, 44, 110, 330, 800, 975, 1150, 150, 150));
        boardField.add(new ResidentialProperty(28, "VENTNOR AVENUE", FieldColor.YELLOW, 260, 22, 44, 110, 330, 800, 975, 1150, 150, 150));
        boardField.add(new WaterWorksProperty(29, "WATER WORKS"));
        boardField.add(new ResidentialProperty(30, "MARVIN GARDENS", FieldColor.YELLOW, 280, 24, 48, 120, 360, 850, 1025, 1200, 150, 150));
        boardField.add(new Field(31, "GO TO JAIL", FieldType.GO_TO_JAIL));

        boardField.add(new ResidentialProperty(32, "PACIFIC AVENUE", FieldColor.GREEN, 300, 26, 52, 130, 390, 900, 1100, 1275, 200, 200));
        boardField.add(new ResidentialProperty(33, "NORTH CAROLINA AVENUE", FieldColor.GREEN, 300, 26, 52, 130, 390, 900, 1100, 1275, 200, 200));
        boardField.add(new Field(34, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));
        boardField.add(new ResidentialProperty(35, "PENNSYLVANIA AVENUE", FieldColor.GREEN, 320, 28, 56, 150, 450, 1000, 1200, 1400, 200, 200));
        boardField.add(new RailroadProperty(36, "SHORT LINE RAILROAD"));
        boardField.add(new Field(37, "CHANCE", FieldType.CHANCE));
        boardField.add(new ResidentialProperty(38, "PARK PLACE", FieldColor.DARK_BLUE, 350, 35, 70, 175, 500, 1100, 1300, 1500, 200, 200));
        boardField.add(new Field(39, "LUXURY TAX", FieldType.TAX));
        boardField.add(new ResidentialProperty(40, "BOARDWALK", FieldColor.DARK_BLUE, 400, 50, 100, 200, 600, 1400, 1700, 2000, 200, 200));

        return new Board(boardField);
    }

    private List<Card> initializeChanceCards() {
        List<Card> chanceCards = new LinkedList<>();

        chanceCards.add(new Card("Advance to Boardwalk"));
        chanceCards.add(new Card("Advance to Go (Collect $200)"));
        chanceCards.add(new Card("Advance to Illinois Avenue. If you pass Go, collect $200"));
        chanceCards.add(new Card("Advance to St. Charles Place. If you pass Go, collect $200"));
        chanceCards.add(new Card("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay wonder twice the rental to which they are otherwise entitled"));
        chanceCards.add(new Card("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay wonder twice the rental to which they are otherwise entitled"));
        chanceCards.add(new Card("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown."));
        chanceCards.add(new Card("Bank pays you dividend of $50."));
        chanceCards.add(new Card("Get Out of Jail Free."));
        chanceCards.add(new Card("Go Back 3 Spaces."));
        chanceCards.add(new Card("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200."));
        chanceCards.add(new Card(" Make general repairs on all your property. For each house pay $25. For each hotel pay $100."));
        chanceCards.add(new Card("Speeding fine $15."));
        chanceCards.add(new Card("Take a trip to Reading Railroad. If you pass Go, collect $200."));
        chanceCards.add(new Card("You have been elected Chairman of the Board. Pay each player $50."));
        chanceCards.add(new Card("Your building loan matures. Collect $150"));

        return chanceCards;
    }


    private List<Card> initializeCommunityChestCards() {
        List<Card> communityChestCards = new LinkedList<>();

        communityChestCards.add(new Card("Advance to Go (Collect $200)"));
        communityChestCards.add(new Card("Bank error in your favor. Collect $200"));
        communityChestCards.add(new Card("Doctor’s fee. Pay $50"));
        communityChestCards.add(new Card("From sale of stock you get $50"));
        communityChestCards.add(new Card("Get Out of Jail Free"));
        communityChestCards.add(new Card("Go to Jail. Go directly to jail, do not pass Go, do not collect $200"));
        communityChestCards.add(new Card("Holiday fund matures. Receive $100"));
        communityChestCards.add(new Card("Income tax refund. Collect $20"));
        communityChestCards.add(new Card("It is your birthday. Collect $10 from every player"));
        communityChestCards.add(new Card("Life insurance matures. Collect $100"));
        communityChestCards.add(new Card("Pay hospital fees of $100"));
        communityChestCards.add(new Card("Pay school fees of $50"));
        communityChestCards.add(new Card("Receive $25 consultancy fee"));
        communityChestCards.add(new Card("You are assessed for street repair. $40 per house. $115 per hotel"));
        communityChestCards.add(new Card("You have won second prize in a beauty contest. Collect $10"));
        communityChestCards.add(new Card("You inherit $100"));


        return communityChestCards;
    }

}
