package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.PropertyField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionService {
    private final GameService gameService;

    public TransactionService(GameService gameService) {
        this.gameService = gameService;
    }

    public void buyProperty(Game game, Player player) {
        Field field = gameService.findFieldById(game, player.getPosition().getId());

        if (player.isAfterRoll() == true) {

            if (checkIfFieldIsAvailableToBuy(field)) {
                PropertyField propertyField = (PropertyField) field;

                if (checkIfPlayerHasEnoughMoney(player, propertyField)) {
                    propertyField.setOwner(player);
                    propertyField.setAvailable(false);
                    payForProperty(player, propertyField);
                    addPropertyToPlayer(player, propertyField);
                    log.info("Player {} has bought the property: {}", player.getNickname(), propertyField.getId());
                }

            } else {
                throw new IllegalStateException("you can't buy this property");
            }
        } else {
            throw new IllegalStateException("you must move first.");
        }
    }


    private boolean checkIfFieldIsAvailableToBuy(Field field) {
        if (!(field instanceof PropertyField)) {
            return false;
        } else {
            if (((PropertyField) field).getOwner() == null) {
                return true;
            }
        }
        return false;
    }


    private void addPropertyToPlayer(Player player, PropertyField propertyField) {
        List<PropertyField> properties = player.getProperties();
        properties.add(propertyField);
        player.setProperties(properties);
    }


    private void payForProperty(Player player, PropertyField propertyField) {
        player.setMoney(player.getMoney() - propertyField.getPrice());
    }


    private boolean checkIfPlayerHasEnoughMoney(Player player, PropertyField propertyField) {
        if (player.getMoney() >= propertyField.getPrice()) {
            return true;
        } else {
            return false;
        }
    }
}
