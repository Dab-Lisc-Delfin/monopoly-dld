package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.Collectors;

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


    public Field findFieldById(Game game, int fieldId) {
        return game.getBoard().getFields().stream()
                .filter(field -> field.getId() == fieldId)
                .collect(Collectors.toList()).get(0);
    }


    public Field findFieldByName(Game game, String fieldName) {
        return game.getBoard().getFields().stream()
                .filter(field -> field.getName() == fieldName)
                .collect(Collectors.toList()).get(0);
    }


    public Player addPlayerToGame(String gameId, String playerNick) {
        Game game = gameManagerService.getGameById(gameId);

        Player player = new Player();
        if (game.getPlayers().size() < 6) {
            player.setPlayerIndex(game.getPlayers().size() + 1);
            player.setDices(null);
            player.setMoney(1500);
//            player.setCurrentFiled();

        } else {
            throw new RuntimeException("lobby is full");
        }


        return null;
    }
}
