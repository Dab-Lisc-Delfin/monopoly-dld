package com.dld.monopoly.model.fields;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ResidentialPropertyTest {

    private ResidentialProperty mockProperty;

    @BeforeEach
    void initialize() {
        mockProperty = new ResidentialProperty(1, "testProperty", FieldColor.LIGHT_BLUE, 100, 50, 100, 150, 200, 300, 400, 500, 100, 200);
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
    void getRentCost_whenOwnColorSet() {
        mockProperty.setHasColorSet(true);
        assertEquals(100, mockProperty.getRentCost());
    }


    @Test
    void getRentCost_whenOwnJustProperty() {
        assertEquals(50, mockProperty.getRentCost());
    }
}

