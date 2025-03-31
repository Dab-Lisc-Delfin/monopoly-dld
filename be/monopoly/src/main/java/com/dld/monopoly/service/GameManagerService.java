package com.dld.monopoly.service;

import com.dld.monopoly.model.Board;
import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.fields.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameManagerService {
    private GameManager gameManager;

    public GameManagerService(GameManager gameManager) {
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

        boardField.add(new NonPropertyField(1, "START", FieldType.START));

        boardField.add(new ResidentialProperty(2, "MEDITERRANEAN AVENUE", FieldType.PROPERTY, FieldColor.BROWN, 60, 2, 4, 10, 30, 90, 160, 250, 50, 50));
        boardField.add(new NonPropertyField(3, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));
        boardField.add(new ResidentialProperty(4, "BALTIC AVENUE", FieldType.PROPERTY, FieldColor.BROWN, 60, 4, 8, 20, 60, 180, 320, 450, 50, 50));
        boardField.add(new NonPropertyField(5, "INCOME TAX", FieldType.TAX));
        boardField.add(new RailroadProperty(6, "READING RAILROAD", FieldType.RAILROADS, 200));
        boardField.add(new ResidentialProperty(7, "ORIENTAL AVENUE", FieldType.PROPERTY, FieldColor.LIGHT_BLUE, 100, 6, 12, 30, 90, 270, 400, 550, 50, 50));
        boardField.add(new NonPropertyField(8, "CHANCE", FieldType.CHANCE));
        boardField.add(new ResidentialProperty(9, "VERMONT AVENUE", FieldType.PROPERTY, FieldColor.LIGHT_BLUE, 100, 6, 12, 30, 90, 270, 400, 550, 50, 50));
        boardField.add(new ResidentialProperty(10, "CONNECTICUT AVENUE", FieldType.PROPERTY, FieldColor.LIGHT_BLUE, 120, 8, 16, 40, 100, 300, 450, 600, 50, 50));
        boardField.add(new NonPropertyField(11, "JAIL", FieldType.JAIL));

        boardField.add(new ResidentialProperty(12, "ST. CHARLES PLACE", FieldType.PROPERTY, FieldColor.PINK, 140, 10, 20, 50, 150, 450, 625, 750, 100, 100));
        boardField.add(new ElectricProperty(13, "ELECTRIC COMPANY", FieldType.ELECTRICITY, 150));
        boardField.add(new ResidentialProperty(14, "STATES AVENUE", FieldType.PROPERTY, FieldColor.PINK, 140, 10, 20, 50, 150, 450, 625, 750, 100, 100));
        boardField.add(new ResidentialProperty(15, "VIRGINIA AVENUE", FieldType.PROPERTY, FieldColor.PINK, 160, 12, 24, 60, 180, 500, 700, 900, 100, 100));
        boardField.add(new RailroadProperty(16, "PENNSYLVANIA RAILROAD", FieldType.RAILROADS, 200));
        boardField.add(new ResidentialProperty(17, "ST. JAMES PLACE", FieldType.PROPERTY, FieldColor.ORANGE, 180, 14, 28, 70, 200, 550, 750, 950, 100, 100));
        boardField.add(new NonPropertyField(18, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));
        boardField.add(new ResidentialProperty(19, "TENNESSEE AVENUE", FieldType.PROPERTY, FieldColor.ORANGE, 180, 14, 28, 70, 200, 550, 750, 950, 100, 100));
        boardField.add(new ResidentialProperty(20, "NEW YORK AVENUE", FieldType.PROPERTY, FieldColor.ORANGE, 200, 16, 32, 80, 220, 600, 800, 1000, 100, 100));
        boardField.add(new NonPropertyField(21, "FREE PARKING", FieldType.FREE_PARKING));

        boardField.add(new ResidentialProperty(22, "KENTUCKY AVENUE", FieldType.PROPERTY, FieldColor.RED, 220, 18, 36, 90, 250, 700, 875, 1050, 150, 150));
        boardField.add(new NonPropertyField(23, "CHANCE", FieldType.CHANCE));
        boardField.add(new ResidentialProperty(24, "INDIANA AVENUE", FieldType.PROPERTY, FieldColor.RED, 220, 18, 36, 90, 250, 700, 875, 1050, 150, 150));
        boardField.add(new ResidentialProperty(25, "ILLINOIS AVENUE", FieldType.PROPERTY, FieldColor.RED, 240, 20, 40, 100, 300, 750, 925, 1100, 150, 150));
        boardField.add(new RailroadProperty(26, "B&O RAILROAD", FieldType.RAILROADS, 200));
        boardField.add(new ResidentialProperty(27, "ATLANTIC AVENUE", FieldType.PROPERTY, FieldColor.YELLOW, 260, 22, 44, 110, 330, 800, 975, 1150, 150, 150));
        boardField.add(new ResidentialProperty(28, "VENTNOR AVENUE", FieldType.PROPERTY, FieldColor.YELLOW, 260, 22, 44, 110, 330, 800, 975, 1150, 150, 150));
        boardField.add(new WaterWorksProperty(29, "WATER WORKS", FieldType.WATER_WORKS, 150));
        boardField.add(new ResidentialProperty(30, "MARVIN GARDENS", FieldType.PROPERTY, FieldColor.YELLOW, 280, 24, 48, 120, 360, 850, 1025, 1200, 150, 150));
        boardField.add(new NonPropertyField(31, "GO TO JAIL", FieldType.GO_TO_JAIL));

        boardField.add(new ResidentialProperty(32, "PACIFIC AVENUE", FieldType.PROPERTY, FieldColor.GREEN, 300, 26, 52, 130, 390, 900, 1100, 1275, 200, 200));
        boardField.add(new ResidentialProperty(33, "NORTH CAROLINA AVENUE", FieldType.PROPERTY, FieldColor.GREEN, 300, 26, 52, 130, 390, 900, 1100, 1275, 200, 200));
        boardField.add(new NonPropertyField(34, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));
        boardField.add(new ResidentialProperty(35, "PENNSYLVANIA AVENUE", FieldType.PROPERTY, FieldColor.GREEN, 320, 28, 56, 150, 450, 1000, 1200, 1400, 200, 200));
        boardField.add(new RailroadProperty(36, "SHORT LINE RAILROAD", FieldType.RAILROADS, 200));
        boardField.add(new NonPropertyField(37, "CHANCE", FieldType.CHANCE));
        boardField.add(new ResidentialProperty(38, "PARK PLACE", FieldType.PROPERTY, FieldColor.DARK_BLUE, 350, 35, 70, 175, 500, 1100, 1300, 1500, 200, 200));
        boardField.add(new NonPropertyField(39, "LUXURY TAX", FieldType.TAX));
        boardField.add(new ResidentialProperty(40, "BOARDWALK", FieldType.PROPERTY, FieldColor.DARK_BLUE, 400, 50, 100, 200, 600, 1400, 1700, 2000, 200, 200));

        return new Board(boardField);
    }


}
