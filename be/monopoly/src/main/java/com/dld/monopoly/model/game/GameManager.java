package com.dld.monopoly.model.game;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GameManager {

    private static GameManager instance;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }


    private List<Game> activeGames = new ArrayList<>();

    public void addGame(Game game) {
        activeGames.add(game);
    }

    public void deleteGame(String gameId) {
        for (Game game : activeGames) {
            if (game.getGameId().equals(gameId)) {
                activeGames.remove(game);
                return;
            }
        }
    }

    public Game getGameById(String gameId) {
        for (Game game : activeGames) {
            if (game.getGameId().equals(gameId)) {
                return game;
            }
        }

        throw new IllegalArgumentException(String.format("Game with ID %s doesn't exist.", gameId));
    }

    public List<Game> getActiveGames() {
        return activeGames;
    }

}
