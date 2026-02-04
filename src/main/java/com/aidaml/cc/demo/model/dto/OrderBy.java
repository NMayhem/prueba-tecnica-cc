package com.aidaml.cc.demo.model.dto;

public enum OrderBy {

    ID("id"),
    EMAIL("email"),
    NAME("name"),
    PHONE("phone"),
    TAXID("taxId"),
    CREATEDAT("createdAt");

    private final String name;

    OrderBy(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
