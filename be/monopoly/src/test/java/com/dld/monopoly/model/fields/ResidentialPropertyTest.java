package com.dld.monopoly.model.fields;

import com.dld.monopoly.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ResidentialPropertyTest {

    private ResidentialProperty mockProperty;
    private Player player;

    @BeforeEach
    void initialize() {
        mockProperty = new ResidentialProperty(1, "testProperty", FieldColor.LIGHT_BLUE, 100, 50, 100, 150, 200, 300, 400, 500, 100, 200);
        player = new Player();

        mockProperty.setOwner(player);
        player.getProperties().add(mockProperty);
    }

    @Test
    void getRentCost_when1Hotel() {
        mockProperty.setHotelsAmount(1);
        assertEquals(500, mockProperty.getRentCost());
    }


    @Test
    void getRentCost_whenOwnHouses() {
        mockProperty.setHousesAmount(4);
        assertEquals(400, mockProperty.getRentCost());

        mockProperty.setHousesAmount(3);
        assertEquals(300, mockProperty.getRentCost());

        mockProperty.setHousesAmount(2);
        assertEquals(200, mockProperty.getRentCost());

        mockProperty.setHousesAmount(1);
        assertEquals(150, mockProperty.getRentCost());
    }


    @Test
    void getRentCost_whenOwnColorSet_when3PropertiesHave3Colors() {
        ResidentialProperty mockProperty2 = new ResidentialProperty(2, "testProperty", FieldColor.LIGHT_BLUE, 100, 50, 100, 150, 200, 300, 400, 500, 100, 200);
        ResidentialProperty mockProperty3 = new ResidentialProperty(3, "testProperty", FieldColor.LIGHT_BLUE, 100, 50, 100, 150, 200, 300, 400, 500, 100, 200);

        player.getProperties().add(mockProperty2);
        player.getProperties().add(mockProperty3);

        assertEquals(100, mockProperty.getRentCost());
    }

    @Test
    void getRentCost_whenOwnColorSet_when3PropertiesHave2Colors() {
        ResidentialProperty mockProperty10 = new ResidentialProperty(10, "testProperty", FieldColor.BROWN, 100, 50, 100, 150, 200, 300, 400, 500, 100, 200);
        ResidentialProperty mockProperty11 = new ResidentialProperty(11, "testProperty", FieldColor.BROWN, 100, 50, 100, 150, 200, 300, 400, 500, 100, 200);

        mockProperty10.setOwner(player);
        player.getProperties().add(mockProperty10);
        player.getProperties().add(mockProperty11);

        assertEquals(100, mockProperty10.getRentCost());
    }


    @Test
    void getRentCost_whenOwnJustProperty() {
        assertEquals(50, mockProperty.getRentCost());
    }
}

