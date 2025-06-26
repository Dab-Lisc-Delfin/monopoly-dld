package com.dld.monopoly.service;

import com.dld.monopoly.model.*;
import com.dld.monopoly.model.fields.*;
import com.dld.monopoly.model.fields.builder.ResidentalPropertyBuilder;
import com.dld.monopoly.model.game.Game;
import com.dld.monopoly.model.game.GameManager;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GameServiceImpl implements GameService {
    private final GameManager gameManager;

    public GameServiceImpl() {
        this.gameManager = GameManager.getInstance();
    }


    public Player addPlayerToGame(String gameId, String playerNick) {
        Game game = gameManager.getGameById(gameId);
        Player player = new Player();

        if (game.isStarted()) {
            throw new IllegalStateException("Can't add a new Player. Game has already started.");
        }

        if (game.getPlayers().size() < 6) {
            player.setPlayerIndex(game.getPlayers().size() + 1);
            player.setNickname(playerNick);
            Field field = findFieldById(game, 1);
            player.setPosition(field);

            game.getPlayers().add(player);
            updateGame(game);
        } else {
            throw new RuntimeException("lobby is full");
        }

        return player;
    }


    public Game createNewGame() {
        String randomId = UUID.randomUUID().toString().substring(0, 5);
        Game game = new Game(randomId, initializeBoard());
        game.setPlayers(new ArrayList<>());
        System.out.println("GAMEEE ID " + game.getGameId());
        return game;
    }


    public Field findFieldById(Game game, int fieldId) {
        return game.getBoard().getFields().stream()
                .filter(field -> field.getId() == fieldId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Field " + fieldId + " doesn't exist"));
    }


    public Field findFieldByName(Game game, String fieldName) {
        return game.getBoard().getFields().stream()
                .filter(field -> field.getName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Field " + fieldName + " doesn't exist"));
    }

    @Override
    public List<Card> shuffleCards(List<Card> cards) {
        Random rnd = new Random();
        int randomNum;

        List<Integer> alreadyUsedNums = new ArrayList<>();
        List<Card> shuffledDeck = new LinkedList<>();

        while (shuffledDeck.size() != cards.size()) {
            randomNum = rnd.nextInt(cards.size());
            if (!alreadyUsedNums.contains(randomNum)) {
                alreadyUsedNums.add(randomNum);
                shuffledDeck.add(cards.get(randomNum));
            }
        }

        return shuffledDeck;
    }


    private void updateGame(Game updatedGame) {
        for (int i = 0; i < gameManager.getActiveGames().size(); i++) {
            if (gameManager.getActiveGames().get(i).getGameId().equals(updatedGame.getGameId())) {
                gameManager.getActiveGames().set(i, updatedGame);
            }
        }
    }


    protected Board initializeBoard() {
        List<Field> boardField = new ArrayList<>();
        ResidentalPropertyBuilder builder = new ResidentalPropertyBuilder();

        boardField.add(new Field(1, "START", FieldType.START));

        boardField.add(
                builder
                        .setId(2)
                        .setName("MEDITERRANEAN AVENUE")
                        .setFieldColor(FieldColor.BROWN)
                        .setPrice(60)
                        .setRentPrices(2, 4, 10, 30, 90, 160, 250)
                        .setBuildingPrices(50, 50)
                        .createObject()
        );
        boardField.add(new Field(3, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));

        boardField.add(builder
                .setId(4)
                .setName("BALTIC AVENUE")
                .setFieldColor(FieldColor.BROWN)
                .setPrice(60)
                .setRentPrices(4, 8, 20, 60, 180, 320, 450)
                .setBuildingPrices(50, 50)
                .createObject()
        );

        boardField.add(new Field(5, "INCOME TAX", FieldType.TAX));

        boardField.add(new RailroadProperty(6, "READING RAILROAD"));

        boardField.add(builder
                .setId(7)
                .setName("ORIENTAL AVENUE")
                .setFieldColor(FieldColor.LIGHT_BLUE)
                .setPrice(100)
                .setRentPrices(6, 12, 30, 90, 270, 400, 550)
                .setBuildingPrices(50, 50)
                .createObject()
        );


        boardField.add(new Field(8, "CHANCE", FieldType.CHANCE));

        boardField.add(builder
                .setId(9)
                .setName("VERMONT AVENUE")
                .setFieldColor(FieldColor.LIGHT_BLUE)
                .setPrice(100)
                .setRentPrices(6, 12, 30, 90, 270, 400, 550)
                .setBuildingPrices(50, 50)
                .createObject()
        );

        boardField.add(builder
                .setId(10)
                .setName("CONNECTICUT AVENUE")
                .setFieldColor(FieldColor.LIGHT_BLUE)
                .setPrice(120)
                .setRentPrices(8, 16, 40, 100, 300, 450, 600)
                .setBuildingPrices(50, 50)
                .createObject()
        );

        boardField.add(new Field(11, "JAIL", FieldType.JAIL));

        boardField.add(builder
                .setId(12)
                .setName("ST. CHARLES PLACE")
                .setFieldColor(FieldColor.PINK)
                .setPrice(140)
                .setRentPrices(10, 20, 50, 150, 450, 625, 750)
                .setBuildingPrices(100, 100)
                .createObject()
        );


        boardField.add(new UtilityProperty(13, "ELECTRIC COMPANY"));

        boardField.add(builder
                .setId(14)
                .setName("STATES AVENUE")
                .setFieldColor(FieldColor.PINK)
                .setPrice(140)
                .setRentPrices(10, 20, 50, 150, 450, 625, 750)
                .setBuildingPrices(100, 100)
                .createObject()
        );


        boardField.add(builder
                .setId(15)
                .setName("VIRGINIA AVENUE")
                .setFieldColor(FieldColor.PINK)
                .setPrice(160)
                .setRentPrices(12, 24, 60, 180, 500, 700, 900)
                .setBuildingPrices(100, 100)
                .createObject()
        );


        boardField.add(new RailroadProperty(16, "PENNSYLVANIA RAILROAD"));

        boardField.add(builder
                .setId(17)
                .setName("ST. JAMES PLACE")
                .setFieldColor(FieldColor.ORANGE)
                .setPrice(180)
                .setRentPrices(14, 28, 70, 200, 550, 750, 950)
                .setBuildingPrices(100, 100)
                .createObject()
        );


        boardField.add(new Field(18, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));

        boardField.add(builder
                .setId(19)
                .setName("TENNESSEE AVENUE")
                .setFieldColor(FieldColor.ORANGE)
                .setPrice(180)
                .setRentPrices(14, 28, 70, 200, 550, 750, 950)
                .setBuildingPrices(100, 100)
                .createObject()
        );


        boardField.add(builder
                .setId(20)
                .setName("NEW YORK AVENUE")
                .setFieldColor(FieldColor.ORANGE)
                .setPrice(200)
                .setRentPrices(16, 32, 80, 220, 600, 800, 1000)
                .setBuildingPrices(100, 100)
                .createObject()
        );

        boardField.add(new Field(21, "FREE PARKING", FieldType.FREE_PARKING));

        boardField.add(builder
                .setId(22)
                .setName("KENTUCKY AVENUE")
                .setFieldColor(FieldColor.RED)
                .setPrice(220)
                .setRentPrices(18, 36, 90, 250, 700, 875, 1050)
                .setBuildingPrices(150, 150)
                .createObject()
        );


        boardField.add(new Field(23, "CHANCE", FieldType.CHANCE));

        boardField.add(builder
                .setId(24)
                .setName("INDIANA AVENUE")
                .setFieldColor(FieldColor.RED)
                .setPrice(220)
                .setRentPrices(18, 36, 90, 250, 700, 875, 1050)
                .setBuildingPrices(150, 150)
                .createObject()
        );


        boardField.add(builder
                .setId(25)
                .setName("ILLINOIS AVENUE")
                .setFieldColor(FieldColor.RED)
                .setPrice(240)
                .setRentPrices(20, 40, 100, 300, 750, 925, 1100)
                .setBuildingPrices(150, 150)
                .createObject()
        );


        boardField.add(new RailroadProperty(26, "B&O RAILROAD"));

        boardField.add(builder
                .setId(27)
                .setName("ATLANTIC AVENUE")
                .setFieldColor(FieldColor.YELLOW)
                .setPrice(260)
                .setRentPrices(22, 44, 110, 330, 800, 975, 1150)
                .setBuildingPrices(150, 150)
                .createObject()
        );


        boardField.add(builder
                .setId(28)
                .setName("VENTNOR AVENUE")
                .setFieldColor(FieldColor.YELLOW)
                .setPrice(260)
                .setRentPrices(22, 44, 110, 330, 800, 975, 1150)
                .setBuildingPrices(150, 150)
                .createObject()
        );


        boardField.add(new UtilityProperty(29, "WATER WORKS"));

        boardField.add(builder
                .setId(30)
                .setName("MARVIN GARDENS")
                .setFieldColor(FieldColor.YELLOW)
                .setPrice(280)
                .setRentPrices(24, 48, 120, 360, 850, 1025, 1200)
                .setBuildingPrices(150, 150)
                .createObject()
        );


        boardField.add(new Field(31, "GO TO JAIL", FieldType.GO_TO_JAIL));

        boardField.add(builder
                .setId(32)
                .setName("PACIFIC AVENUE")
                .setFieldColor(FieldColor.GREEN)
                .setPrice(300)
                .setRentPrices(26, 52, 130, 390, 900, 1100, 1275)
                .setBuildingPrices(200, 200)
                .createObject()
        );

        boardField.add(builder
                .setId(33)
                .setName("NORTH CAROLINA AVENUE")
                .setFieldColor(FieldColor.GREEN)
                .setPrice(300)
                .setRentPrices(26, 52, 130, 390, 900, 1100, 1275)
                .setBuildingPrices(200, 200)
                .createObject()
        );

        boardField.add(new Field(34, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));

        boardField.add(builder
                .setId(35)
                .setName("PENNSYLVANIA AVENUE")
                .setFieldColor(FieldColor.GREEN)
                .setPrice(320)
                .setRentPrices(28, 56, 150, 450, 1000, 1200, 1400)
                .setBuildingPrices(200, 200)
                .createObject()
        );


        boardField.add(new RailroadProperty(36, "SHORT LINE RAILROAD"));

        boardField.add(new Field(37, "CHANCE", FieldType.CHANCE));

        boardField.add(builder
                .setId(38)
                .setName("PARK PLACE")
                .setFieldColor(FieldColor.DARK_BLUE)
                .setPrice(350)
                .setRentPrices(35, 70, 175, 500, 1100, 1300, 1500)
                .setBuildingPrices(200, 200)
                .createObject()
        );


        boardField.add(new Field(39, "LUXURY TAX", FieldType.TAX));

        boardField.add(new ResidentialProperty(40, "BOARDWALK", FieldColor.DARK_BLUE, 400, 50, 100, 200, 600, 1400, 1700, 2000, 200, 200));

        boardField.add(builder
                .setId(40)
                .setName("BOARDWALK")
                .setFieldColor(FieldColor.DARK_BLUE)
                .setPrice(400)
                .setRentPrices(50, 100, 200, 600, 1400, 1700, 2000)
                .setBuildingPrices(200, 200)
                .createObject()
        );

        return new Board(boardField);
    }

    private void finishTour(Game game) {
        game.getCurrentPlayer().setAfterRoll(false);
    }

}
