package com.dld.monopoly.model.fields;

import com.dld.monopoly.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityPropertyTest {

    private UtilityProperty mockUtilityProperty;
    private Player player;

    @BeforeEach
    void initialize() {
        this.mockUtilityProperty = new UtilityProperty(1, "testUtilityProperty");
        this.player = new Player();

        mockUtilityProperty.setOwner(player);
        player.getProperties().add(mockUtilityProperty);
    }


    @Test
    void getRentCost_whenOwnBothUtilities() {
        UtilityProperty mockUtilityProperty2 = new UtilityProperty(2, "testUtilityProperty2");
        player.getProperties().add(mockUtilityProperty2);

        int dices = 3;

        assertEquals(30, mockUtilityProperty.getRentCost(dices));

        dices = 4;
        assertEquals(40, mockUtilityProperty.getRentCost(dices));

        dices = 5;
        assertEquals(50, mockUtilityProperty.getRentCost(dices));

        dices = 6;
        assertEquals(60, mockUtilityProperty.getRentCost(dices));

        dices = 7;
        assertEquals(70, mockUtilityProperty.getRentCost(dices));

        dices = 8;
        assertEquals(80, mockUtilityProperty.getRentCost(dices));

        dices = 9;
        assertEquals(90, mockUtilityProperty.getRentCost(dices));

        dices = 10;
        assertEquals(100, mockUtilityProperty.getRentCost(dices));

        dices = 11;
        assertEquals(110, mockUtilityProperty.getRentCost(dices));
    }


    @Test
    void getRentCost_whenOwnJustOneUtility() {
        int dices = 3;
        assertEquals(12, mockUtilityProperty.getRentCost(dices));

        dices = 4;
        assertEquals(16, mockUtilityProperty.getRentCost(dices));

        dices = 5;
        assertEquals(20, mockUtilityProperty.getRentCost(dices));

        dices = 6;
        assertEquals(24, mockUtilityProperty.getRentCost(dices));

        dices = 7;
        assertEquals(28, mockUtilityProperty.getRentCost(dices));

        dices = 8;
        assertEquals(32, mockUtilityProperty.getRentCost(dices));

        dices = 9;
        assertEquals(36, mockUtilityProperty.getRentCost(dices));

        dices = 10;
        assertEquals(40, mockUtilityProperty.getRentCost(dices));

        dices = 11;
        assertEquals(44, mockUtilityProperty.getRentCost(dices));
    }


    @Test
    void getRentCost_whenIncorrectDicesAmount() {
        int dices2 = 2;
        assertThrows(IllegalArgumentException.class, () -> mockUtilityProperty.getRentCost(dices2));

        int dices12 = 12;
        assertThrows(IllegalArgumentException.class, () -> mockUtilityProperty.getRentCost(dices12));
    }

}