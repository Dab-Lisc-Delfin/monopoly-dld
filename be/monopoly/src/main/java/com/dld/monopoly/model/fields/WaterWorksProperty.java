package com.dld.monopoly.model.fields;

public class WaterWorksProperty extends PropertyField implements Rentable{
    public WaterWorksProperty(int id, String name, FieldType fieldType, int price) {
        super(id, name, fieldType, price);
    }


    @Override
    public int getRentCost() {
        return 0;//todo
    }
}
