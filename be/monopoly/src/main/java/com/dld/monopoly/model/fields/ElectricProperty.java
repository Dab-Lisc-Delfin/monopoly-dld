package com.dld.monopoly.model.fields;

public class ElectricProperty extends PropertyField implements Rentable{
    public ElectricProperty(int id, String name, FieldType fieldType, int price) {
        super(id, name, fieldType, price);
    }

    @Override
    public int getRentCost() {
        return 0;//todo
    }
}
