package com.dld.monopoly.model.fields;

import com.dld.monopoly.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RailroadProperty extends Field implements Rentable {

    public RailroadProperty(int id, String name) {
        super(id, name, FieldType.RAILROADS);
        this.price = 200;
        this.isAvailable = true;
        this.owner = null;
        this.oneRailroadRent = 25;
        this.twoRailroadRent = 50;
        this.threeRailroadRent = 100;
        this.fourRailroadRent = 200;
    }

    private final int oneRailroadRent;
    private final int twoRailroadRent;
    private final int threeRailroadRent;
    private final int fourRailroadRent;
    private final int price;
    private boolean isAvailable;
    private Player owner;

    @Override
    public int getRentCost() {
        return 0;//todo
    }

}
