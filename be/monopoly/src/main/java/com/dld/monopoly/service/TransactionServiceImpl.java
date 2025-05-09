package com.dld.monopoly.service;

import com.dld.monopoly.model.Game;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.model.fields.Field;
import com.dld.monopoly.model.fields.RentableProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
                            field.setOwner(player);
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

    public void payOwnerOfTheProperty(Player owner, Player playerWhoPays) {
        //todo
    }


    private boolean checkIfFieldIsAvailableToBuy(Field field) {
        if (!(field instanceof RentableProperty)) {
            return false;
        } else {
            if (((RentableProperty) field).getOwner() == null) {
                return true;
            }
        }
        return false;
    }


    private void addPropertyToPlayer(Player player, RentableProperty rentableProperty) {
        player.getProperties().add(rentableProperty);
        rentableProperty.setOwner(player);
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
