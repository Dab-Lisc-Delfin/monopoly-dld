package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.FieldType;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MoveService {

    private GameService gameService;

    public MoveService(GameService gameService) {
        this.gameService = gameService;
    }


    public int[] throwDices() {
        int[] dices = new int[2];
        Random rnd = new Random();
        int dice1 = rnd.nextInt(6) + 1;
        int dice2 = rnd.nextInt(6) + 1;

        System.out.println(dice1);
        System.out.println(dice2);

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

    public Field makeMove(Game game, Player player) {
//        check if it's your turn //todo

        //if (not doublet) -> move
        int currentPosition = player.getPosition().getId();
        int moveLength = player.getAllDiceRollsInThisTour();
        int newPositionId = 0;

        if (currentPosition + moveLength > 40) {
            newPositionId = (currentPosition + moveLength) - 40;
            Field field = gameService.findFieldById(game, newPositionId);

            makeMoveDependingOfField(field, game, player);
            passStart(player);


        } else {
            newPositionId = currentPosition + moveLength;
            Field field = gameService.findFieldById(game, newPositionId);

            makeMoveDependingOfField(field, game, player);
        }

        // if(isAvailable to buy?){ buy/don't
        // else{payOwner}
        //else doublet counter++;

        return null;
    }


    public void makeMoveDependingOfField(Field field, Game game, Player player) {
        switch (field.getFieldType()) {
            case FieldType.START -> {
                moveToProperty(player, field);
            }
            case FieldType.PROPERTY -> {
                moveToProperty(player, field);
                ifPropertyHasOwnerPayHim();
            }
            case FieldType.CHANCE -> {
                moveToProperty(player, field);
                getChanceCard(game, player);
            }
            case FieldType.COMMUNITY_CHEST -> {
                moveToProperty(player, field);
                getCommunityChestCard(game, player);
            }
            case FieldType.RAILROADS -> {
                moveToProperty(player, field);
                ifRailroadHasOwnerPayHim();
            }
            case FieldType.TAX -> {
                moveToProperty(player, field);
                payTax(player, field);
            }
            case FieldType.ELECTRICITY -> {
                moveToProperty(player, field);
                ifElectricityHasOwnerPayHim(player);
            }
            case FieldType.WATER_WORKS -> {
                moveToProperty(player, field);
                ifWaterWorksHasOwnerPayHim(player);
            }
            case FieldType.FREE_PARKING -> moveToProperty(player, field);
            case FieldType.JAIL -> moveToProperty(player, field);
            case FieldType.GO_TO_JAIL -> sendToJail(game, player);
        }
    }

    private void sendToJail(Game game, Player player) {
        Field jail = gameService.findFieldByName(game, "JAIL");

        player.setPosition(jail);
        player.setInJail(true);
    }

    private void moveToProperty(Player player, Field field) {
        player.setPosition(field);
    }

    private void passStart(Player player) {
        player.setMoney(player.getMoney() + 200);
    }

    private void ifPropertyHasOwnerPayHim() {
        System.out.println("checking if property has its owner...");
    }

    private void getChanceCard(Game game, Player player) {
        System.out.println("getting chance card...");
    }

    private void getCommunityChestCard(Game game, Player player) {
        System.out.println("getting community chest card...");
    }

    private void ifRailroadHasOwnerPayHim() {
        System.out.println("checking if railroad has its owner...");
    }

    private void payTax(Player player, Field field) {
        if (field.getName().equals("INCOME TAX")) {
            player.setMoney(player.getMoney() - 200);
        } else if (field.getName().equals("LUXURY TAX")) {
            player.setMoney(player.getMoney() - 100);
        }
    }

    private void ifElectricityHasOwnerPayHim(Player player) {
        System.out.println("checking if electricity has its owner");
    }

    private void ifWaterWorksHasOwnerPayHim(Player player) {
        System.out.println("checking if water works has its owner");
    }

    public void handleDoublet(Player player) {
        int playerDoublets = player.getDoubletCounter();
        if (playerDoublets < 3) {
            player.setDoubletCounter(playerDoublets + 1);
        } else {
            throw new IllegalArgumentException("Player " + player.getNickname() + " has already 3 doublets.");
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

}
