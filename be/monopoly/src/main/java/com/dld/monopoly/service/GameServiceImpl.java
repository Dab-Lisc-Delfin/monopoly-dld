package com.dld.monopoly.service;

import com.dld.monopoly.model.Card;
import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.GameManager;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


@Service
public class GameServiceImpl implements GameService {
    private final GameManager gameManager;
    private final GameManagerServiceImpl gameManagerServiceImpl;

    public GameServiceImpl(GameManager gameManager, GameManagerServiceImpl gameManagerServiceImpl) {
        this.gameManager = gameManager;
        this.gameManagerServiceImpl = gameManagerServiceImpl;
    }


    public Player addPlayerToGame(String gameId, String playerNick) {
        Game game = gameManagerServiceImpl.getGameById(gameId);
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

    private void finishTour(Game game) {
        game.getCurrentPlayer().setAfterRoll(false);
    }

}
