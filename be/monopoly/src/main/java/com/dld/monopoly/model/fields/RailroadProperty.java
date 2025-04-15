package com.dld.monopoly.model.fields;

import com.dld.monopoly.model.Player;

public class RailroadProperty extends Field implements Rentable{

    public RailroadProperty(int id, String name, FieldType fieldType, int price) {
        super(id, name, fieldType);
        this.rentWith1Railroads = 25;
        this.rentWith2Railroads = 50;
        this.rentWith3Railroads = 100;
        this.rentWith4Railroads = 200;
    }

    private int rentWith1Railroads;
    private int rentWith2Railroads;
    private int rentWith3Railroads;
    private int rentWith4Railroads;
    private FieldColor color;
    private int price;
    private boolean isAvailable;
    private Player owner;
}

    @Override
    public int getRentCost() {
        return 0;//todo
    }
}
