package com.dld.monopoly.service;

import com.dld.monopoly.model.*;
import com.dld.monopoly.model.fields.*;
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
        boardField.add(new UtilityProperty(13, "ELECTRIC COMPANY"));
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
        boardField.add(new UtilityProperty(29, "WATER WORKS"));
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

    private void finishTour(Game game) {
        game.getCurrentPlayer().setAfterRoll(false);
    }

}
