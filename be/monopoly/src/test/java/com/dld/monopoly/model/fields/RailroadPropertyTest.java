package com.dld.monopoly.model.fields;

import com.dld.monopoly.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RailroadPropertyTest {

    @Test
    void getRentCost_whenPlayerOwnRailroads() {
        RailroadProperty mockRailroadProperty1 = new RailroadProperty(1, "testRailroad1");
        RailroadProperty mockRailroadProperty2 = new RailroadProperty(1, "testRailroad2");
        RailroadProperty mockRailroadProperty3 = new RailroadProperty(1, "testRailroad3");
        RailroadProperty mockRailroadProperty4 = new RailroadProperty(1, "testRailroad4");

        Player testPlayer = new Player();

        mockRailroadProperty1.setOwner(testPlayer);

        testPlayer.getProperties().add(mockRailroadProperty1);
        assertEquals(25, mockRailroadProperty1.getRentCost());

        testPlayer.getProperties().add(mockRailroadProperty2);
        assertEquals(50, mockRailroadProperty1.getRentCost());

        testPlayer.getProperties().add(mockRailroadProperty1);
        assertEquals(100, mockRailroadProperty1.getRentCost());

        testPlayer.getProperties().add(mockRailroadProperty1);
        assertEquals(200, mockRailroadProperty1.getRentCost());
    }
}