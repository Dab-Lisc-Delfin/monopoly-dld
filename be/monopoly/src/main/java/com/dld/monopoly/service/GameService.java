package com.dld.monopoly.service;

import com.dld.monopoly.model.Board;
import com.dld.monopoly.model.fields.*;
import com.dld.monopoly.model.GameManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService {
    private final GameManager gameManager;

    public GameService(GameManager gameManager) {
        this.gameManager = gameManager;
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

    public Board initializeBoard() {
        List<Field> boardField = new ArrayList<>();

        boardField.add(new NonPropertyField(1, "START", FieldType.START));

        boardField.add(new PropertyField(2, "MEDITERRANEAN AVENUE", FieldType.PROPERTY, FieldColor.BROWN, 60));
        boardField.add(new NonPropertyField(3, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));
        boardField.add(new PropertyField(4, "BALTIC AVENUE", FieldType.PROPERTY, FieldColor.BROWN, 60));
        boardField.add(new NonPropertyField(5, "INCOME TAX", FieldType.TAX));
        boardField.add(new PropertyField(6, "READING RAILROAD", FieldType.RAILROADS, FieldColor.NONE, 200));
        boardField.add(new PropertyField(7, "ORIENTAL AVENUE", FieldType.PROPERTY, FieldColor.LIGHT_BLUE, 100));
        boardField.add(new NonPropertyField(8, "CHANCE", FieldType.CHANCE));
        boardField.add(new PropertyField(9, "VERMONT AVENUE", FieldType.PROPERTY, FieldColor.LIGHT_BLUE, 100));
        boardField.add(new PropertyField(10, "CONNECTICUT AVENUE", FieldType.PROPERTY, FieldColor.LIGHT_BLUE, 120));
        boardField.add(new NonPropertyField(11, "JAIL", FieldType.JAIL));

        boardField.add(new PropertyField(12, "ST. CHARLES PLACE", FieldType.PROPERTY, FieldColor.PINK, 140));
        boardField.add(new PropertyField(13, "ELECTRIC COMPANY", FieldType.ELECTRICITY, FieldColor.NONE, 150));
        boardField.add(new PropertyField(14, "STATES AVENUE", FieldType.PROPERTY, FieldColor.PINK, 140));
        boardField.add(new PropertyField(15, "VIRGINIA AVENUE", FieldType.PROPERTY, FieldColor.PINK, 160));
        boardField.add(new PropertyField(16, "PENNSYLVANIA RAILROAD", FieldType.RAILROADS, FieldColor.NONE, 200));
        boardField.add(new PropertyField(17, "ST. JAMES PLACE", FieldType.PROPERTY, FieldColor.ORANGE, 180));
        boardField.add(new NonPropertyField(18, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));
        boardField.add(new PropertyField(19, "TENNESSEE AVENUE", FieldType.PROPERTY, FieldColor.ORANGE, 180));
        boardField.add(new PropertyField(20, "NEW YORK AVENUE", FieldType.PROPERTY, FieldColor.ORANGE, 200));
        boardField.add(new NonPropertyField(21, "FREE PARKING", FieldType.FREE_PARKING));

        boardField.add(new PropertyField(22, "KENTUCKY AVENUE", FieldType.PROPERTY, FieldColor.RED, 220));
        boardField.add(new NonPropertyField(23, "CHANCE", FieldType.CHANCE));
        boardField.add(new PropertyField(24, "INDIANA AVENUE", FieldType.PROPERTY, FieldColor.RED, 220));
        boardField.add(new PropertyField(25, "ILLINOIS AVENUE", FieldType.PROPERTY, FieldColor.RED, 240));
        boardField.add(new PropertyField(26, "B&O RAILROAD", FieldType.RAILROADS, FieldColor.NONE, 200));
        boardField.add(new PropertyField(27, "ATLANTIC AVENUE", FieldType.PROPERTY, FieldColor.YELLOW, 260));
        boardField.add(new PropertyField(28, "VENTNOR AVENUE", FieldType.PROPERTY, FieldColor.YELLOW, 260));
        boardField.add(new NonPropertyField(29, "WATER WORKS", FieldType.WATER_WORKS));
        boardField.add(new PropertyField(30, "MARVIN GARDENS", FieldType.PROPERTY, FieldColor.YELLOW, 280));
        boardField.add(new NonPropertyField(31, "GO TO JAIL", FieldType.GO_TO_JAIL));

        boardField.add(new PropertyField(32, "PACIFIC AVENUE", FieldType.PROPERTY, FieldColor.GREEN, 300));
        boardField.add(new PropertyField(33, "NORTH CAROLINA AVENUE", FieldType.PROPERTY, FieldColor.GREEN, 300));
        boardField.add(new NonPropertyField(34, "COMMUNITY CHEST", FieldType.COMMUNITY_CHEST));
        boardField.add(new PropertyField(35, "PENNSYLVANIA AVENUE", FieldType.PROPERTY, FieldColor.GREEN, 320));
        boardField.add(new PropertyField(36, "SHORT LINE RAILROAD", FieldType.RAILROADS, FieldColor.NONE, 200));
        boardField.add(new NonPropertyField(37, "CHANCE", FieldType.CHANCE));
        boardField.add(new PropertyField(38, "PARK PLACE", FieldType.PROPERTY, FieldColor.DARK_BLUE, 350));
        boardField.add(new NonPropertyField(39, "LUXURY TAX", FieldType.TAX));
        boardField.add(new PropertyField(40, "BOARDWALK", FieldType.PROPERTY, FieldColor.DARK_BLUE, 400));

        return new Board(boardField);
    }
}
