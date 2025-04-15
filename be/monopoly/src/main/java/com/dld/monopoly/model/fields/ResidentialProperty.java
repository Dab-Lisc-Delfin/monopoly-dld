package com.dld.monopoly.model.fields;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentialProperty extends Field implements Rentable {

    public ResidentialProperty(int id, String name, FieldColor color, int price, int rent, int rentWithColorSet, int rentWith1House, int rentWith2House, int rentWith3House, int rentWith4House, int rentWithHotel, int housesCost, int hotelsCost) {
        super(id, name, FieldType.PROPERTY);
        this.price = price;
        this.rent = rent;
        this.color = color;
        this.housesCost = housesCost;
        this.rentWith1House = rentWith1House;
        this.rentWith2House = rentWith2House;
        this.rentWith3House = rentWith3House;
        this.rentWith4House = rentWith4House;
        this.rentWithHotel = rentWithHotel;
        this.rentWithColorSet = rentWithColorSet;
        this.hotelsCost = hotelsCost;

        this.housesAmount = 0;
        this.hotelsAmount = 0;
    }

    private final FieldColor color;
    private final int price;
    private final int rent;
    private final int rentWithColorSet;
    private final int rentWith1House;
    private final int rentWith2House;
    private final int rentWith3House;
    private final int rentWith4House;
    private final int rentWithHotel;

    private final int housesCost;
    private final int hotelsCost;
    private int housesAmount;
    private int hotelsAmount;

    @Override
    public int getRentCost() {
        return 0;//todo
    }
}

//package com.dld.monopoly.model.fields;
//
//import com.dld.monopoly.model.Player;
//
//abstract class RentableProperty extends Field {
//    protected Player owner;
//    protected int price;
//
//    protected RentableProperty(int id, String name, FieldType fieldType, int price) {
//        super(id,name,fieldType);
//        this.owner = null;
//    }
//
//    abstract int getRentCost();
//}
//
////private int id;
////    private String name;
////    private FieldType fieldType;