package com.dld.monopoly.model;

import com.dld.monopoly.model.fields.Field;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Board {
    private List<Field> fields;
}
