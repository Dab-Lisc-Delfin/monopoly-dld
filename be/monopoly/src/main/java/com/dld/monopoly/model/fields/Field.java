package com.dld.monopoly.model.fields;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Field {

    private int id;
    private String name;
    private FieldType fieldType;

}
