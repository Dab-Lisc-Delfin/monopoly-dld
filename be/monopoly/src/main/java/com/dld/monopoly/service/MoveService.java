package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;

public interface MoveService {

    public int[] throwDices();

    public Field makeMove(Game game, Player player);

    public void makeMoveDependingOfField(Field field, Game game, Player player);

    public void handleDoublet(Player player);

    public boolean checkIfDoublet(int[] dices);

}
