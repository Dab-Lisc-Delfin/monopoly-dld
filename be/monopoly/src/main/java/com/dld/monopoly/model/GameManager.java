package com.dld.monopoly.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class GameManager {
    private List<Game> activeGames = new ArrayList<>();

    public void addGame(Game game) {
        activeGames.add(game);
    }

    public List<Game> getActiveGames() {
        return activeGames;
    }
}
