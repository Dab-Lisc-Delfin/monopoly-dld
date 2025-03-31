package com.dld.monopoly.model.fields;

public class RailroadProperty extends PropertyField implements Rentable{

    public RailroadProperty(int id, String name, FieldType fieldType, int price) {
        super(id, name, fieldType, price);
        this.rentWith1Railroads = 25;
        this.rentWith2Railroads = 50;
        this.rentWith3Railroads = 100;
        this.rentWith4Railroads = 200;
    }

    private int rentWith1Railroads;
    private int rentWith2Railroads;
    private int rentWith3Railroads;
    private int rentWith4Railroads;

    @Override
    public int getRentCost() {
        return 0;//todo
    }
}
