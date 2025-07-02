package com.dld.monopoly.service;

import com.dld.monopoly.model.game.Game;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final GameServiceImpl gameServiceImpl;

    public TransactionServiceImpl(GameServiceImpl gameServiceImpl) {
        this.gameServiceImpl = gameServiceImpl;
    }

    public void buyProperty(Game game, Player player) {
        RentableProperty field = (RentableProperty) gameServiceImpl.findFieldById(game, player.getPosition().getId());

        if (field instanceof RentableProperty) {
            if (player.isAfterRoll()) {
                if (checkIfFieldIsAvailableToBuy(field)) {

                    if (field instanceof RentableProperty)

                        if (checkIfPlayerHasEnoughMoneyToBuyProperty(player, field)) {
                            field.setOwnerId(player.getId());
                            field.setAvailable(false);
                            payForProperty(player, field);
                            addPropertyToPlayer(player, field);
                            log.info("Player {} has bought the property: {}", player.getNickname(), field.getId());
                        }

                } else {
                    throw new IllegalStateException("you can't buy this property");
                }
            } else {
                throw new IllegalStateException("you must move first.");
            }
        } else {
            throw new IllegalStateException("you can't buy this property");
        }
    }

    public void payPropertyOwner(Player owner, Player playerWhoPays) {
        FieldType fieldType = playerWhoPays.getCurrentPosition().getFieldType();

        switch (fieldType) {
            case FieldType.PROPERTY -> {
                ResidentialProperty residentialProperty = (ResidentialProperty) playerWhoPays.getCurrentPosition();
                int rentCosts = residentialProperty.getRentCost();

                transferMoney(owner, playerWhoPays, rentCosts);
            }
            case FieldType.RAILROADS -> {
                RailroadProperty railroadProperty = (RailroadProperty) playerWhoPays.getCurrentPosition();
                int rentCosts = railroadProperty.getRentCost();

                transferMoney(owner, playerWhoPays, rentCosts);
            }
            case FieldType.UTILITY -> {
                UtilityProperty utilityProperty = (UtilityProperty) playerWhoPays.getCurrentPosition();
                int rentCosts = utilityProperty.getRentCost();

                transferMoney(owner, playerWhoPays, rentCosts);
            }
        }

    }


    private void transferMoney(Player playerThatGetMoney, Player playerWhoSendMoney, int moneyAmount) {
        playerThatGetMoney.setMoney(playerThatGetMoney.getMoney() + moneyAmount);
        playerWhoSendMoney.setMoney(playerWhoSendMoney.getMoney() - moneyAmount);

    }


    private boolean checkIfFieldIsAvailableToBuy(Field field) {
        if (!(field instanceof RentableProperty)) {
            return false;
        } else {
            if (((RentableProperty) field).getOwnerId() == 0) {
                return true;
            }
        }
        return false;
    }


    private void addPropertyToPlayer(Player player, RentableProperty rentableProperty) {
        player.getProperties().add(rentableProperty);
        rentableProperty.setOwnerId(player.getId());
    }


    private void payForProperty(Player player, RentableProperty rentableProperty) {
        player.setMoney(player.getMoney() - rentableProperty.getPrice());
    }


    private boolean checkIfPlayerHasEnoughMoneyToBuyProperty(Player player, RentableProperty rentableProperty) {
        if (player.getMoney() >= rentableProperty.getPrice()) {
            return true;
        } else {
            return false;
        }
    }
}
