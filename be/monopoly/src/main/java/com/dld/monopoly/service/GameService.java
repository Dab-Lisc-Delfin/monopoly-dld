package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.FieldType;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class GameService {
    private final GameManager gameManager;
    private final GameManagerService gameManagerService;

    public GameService(GameManager gameManager, GameManagerService gameManagerService) {
        this.gameManager = gameManager;
        this.gameManagerService = gameManagerService;
    }

    public int[] throwDices() {
        int[] dices = new int[2];
        Random rnd = new Random();
        int dice1 = rnd.nextInt(6) + 1;
        int dice2 = rnd.nextInt(6) + 1;

        dices[0] = dice1;
        dices[1] = dice2;

        return dices;
    }


    private void addDiceRollSumToPlayer(Player player, int[] dices) {
        if (dices.length == 2) {
            int sum = dices[0] + dices[1];
            player.setAllDiceRollsInThisTour(player.getAllDiceRollsInThisTour() + sum);
        } else {
            throw new RuntimeException("incorrect dices size");
        }
    }


    public boolean checkIfDoublet(int[] dices) {
        if (dices.length == 2) {
            if (dices[0] == dices[1]) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new RuntimeException("incorrect dices size");
        }
    }


    public void handleDoublet(Player player) {
        int playerDoublets = player.getDoubletCounter();
        if (playerDoublets < 3) {
            player.setDoubletCounter(playerDoublets + 1);
        } else {
            throw new IllegalArgumentException("Player " + player.getNickname() + " has already 3 doublets.");
        }
    }


    private void updateGame(Game updatedGame) {
        for (int i = 0; i < gameManager.getActiveGames().size(); i++) {
            if (gameManager.getActiveGames().get(i).getGameId().equals(updatedGame.getGameId())) {
                gameManager.getActiveGames().set(i, updatedGame);
            }
        }
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


    public Field makeMove(Game game, Player player) {
//        check if it's your turn //todo

        //if (not doublet) -> move
        int currentPosition = player.getPosition().getId();
        int moveLength = player.getAllDiceRollsInThisTour();
        int newPositionId = 0;

        if (currentPosition + moveLength > 40) {
            newPositionId = (currentPosition + moveLength) - 40;
            Field field = findFieldById(game, newPositionId);


            switch (field.getFieldType()) {
                case FieldType.PROPERTY ->  sendToJail(game,player); // todo +add rest types and create methods
                case FieldType.GO_TO_JAIL -> sendToJail(game, player);
            }


                player.setPosition(field);


        } else {
            newPositionId = currentPosition + moveLength;
            Field field = findFieldById(game, newPositionId);

            player.setPosition(field);
        }

        // if(isAvailable to buy?){ buy/don't
        // else{payOwner}
        //else doublet counter++;

        return null;
    }


    private void sendToJail(Game game, Player player) {
        Field jail = findFieldByName(game, "JAIL");

        player.setPosition(jail);
        player.setInJail(true);
    }


    public Player addPlayerToGame(String gameId, String playerNick) {
        Game game = gameManagerService.getGameById(gameId);
        Player player = new Player();

        //todo check game status (if game is started can't add a new player)
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
}
