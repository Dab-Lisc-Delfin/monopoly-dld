package com.dld.monopoly.model.fields;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WaterWorksProperty extends Field implements Rentable {
    public WaterWorksProperty(int id, String name) {
        super(id, name, FieldType.UTILITY);
        this.price = 150;
    }

    private final int price;

    @Override
    public int getRentCost() {
        return 0;//todo
    }
}
