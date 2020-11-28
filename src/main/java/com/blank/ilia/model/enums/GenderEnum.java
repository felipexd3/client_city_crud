package com.blank.ilia.model.enums;

public enum GenderEnum {
    M("MASCULINE"),
    F("FEMININE"),
    OU("OTHERS");

    private String value;

    GenderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
