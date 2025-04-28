package com.dld.monopoly.model.fields;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WaterWorksProperty extends RentableProperty {
    public WaterWorksProperty(int id, String name) {
        super(id, name, FieldType.UTILITY, 150);
    }

    @Override
    public int getRentCost() {
        return 0;//todo
    }
}
