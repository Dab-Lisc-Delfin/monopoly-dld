package com.dld.monopoly.model.dto;

import com.dld.monopoly.model.fields.FieldType;

public record FieldDTO(
        int id,
        String name,
        FieldType fieldType,
        int price
) {
}
