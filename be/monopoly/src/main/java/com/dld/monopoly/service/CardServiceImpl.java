package com.dld.monopoly.service.card;

import com.dld.monopoly.model.Card;
import com.dld.monopoly.model.Player;
import com.dld.monopoly.service.card.CardService;

import java.util.*;

public class CardServiceImpl implements CardService {

    public Card getCardFromDeck(List<Card> chanceCards) {

        Card topCard = chanceCards.get(chanceCards.size() - 1);

        //move each card
        for (int i = 1; i < chanceCards.size(); i++) {
            chanceCards.set(i, chanceCards.get(i - 1));
        }

        chanceCards.set(0, topCard);
        return topCard;
    }

    @Override
    public void shuffleDeck(List<Card> deck) {
        Collections.shuffle(deck);
    }

    @Override
    public void useCard(Player player, Card card) {

        switch (card.getName()) {
            case "Your building loan matures. Collect $150" -> {
                
            }
        }


    }

    public List<Card> initializeChanceCards() {
        List<Card> chanceCards = new LinkedList<>();

        chanceCards.add(new Card("Advance to Boardwalk"));
        chanceCards.add(new Card("Advance to Go (Collect $200)"));
        chanceCards.add(new Card("Advance to Illinois Avenue. If you pass Go, collect $200"));
        chanceCards.add(new Card("Advance to St. Charles Place. If you pass Go, collect $200"));
        chanceCards.add(new Card("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay wonder twice the rental to which they are otherwise entitled"));
        chanceCards.add(new Card("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay wonder twice the rental to which they are otherwise entitled"));
        chanceCards.add(new Card("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.")); //TODO
        chanceCards.add(new Card("Bank pays you dividend of $50."));
        chanceCards.add(new Card("Get Out of Jail Free.")); //TODO
        chanceCards.add(new Card("Go Back 3 Spaces."));
        chanceCards.add(new Card("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200."));
        chanceCards.add(new Card("Make general repairs on all your property. For each house pay $25. For each hotel pay $100."));
        chanceCards.add(new Card("Speeding fine $15."));
        chanceCards.add(new Card("Take a trip to Reading Railroad. If you pass Go, collect $200."));
        chanceCards.add(new Card("You have been elected Chairman of the Board. Pay each player $50."));
        chanceCards.add(new Card("Your building loan matures. Collect $150"));

        return chanceCards;
    }


    public List<Card> initializeCommunityChestCards() {
        List<Card> communityChestCards = new LinkedList<>();

        communityChestCards.add(new Card("Advance to Go (Collect $200)"));
        communityChestCards.add(new Card("Bank error in your favor. Collect $200"));
        communityChestCards.add(new Card("Doctor’s fee. Pay $50"));
        communityChestCards.add(new Card("From sale of stock you get $50"));
        communityChestCards.add(new Card("Get Out of Jail Free"));
        communityChestCards.add(new Card("Go to Jail. Go directly to jail, do not pass Go, do not collect $200"));
        communityChestCards.add(new Card("Holiday fund matures. Receive $100"));
        communityChestCards.add(new Card("Income tax refund. Collect $20"));
        communityChestCards.add(new Card("It is your birthday. Collect $10 from every player"));
        communityChestCards.add(new Card("Life insurance matures. Collect $100"));
        communityChestCards.add(new Card("Pay hospital fees of $100"));
        communityChestCards.add(new Card("Pay school fees of $50"));
        communityChestCards.add(new Card("Receive $25 consultancy fee"));
        communityChestCards.add(new Card("You are assessed for street repair. $40 per house. $115 per hotel"));
        communityChestCards.add(new Card("You have won second prize in a beauty contest. Collect $10"));
        communityChestCards.add(new Card("You inherit $100"));


        return communityChestCards;
    }

}
