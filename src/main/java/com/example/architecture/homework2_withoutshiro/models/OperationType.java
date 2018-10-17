package com.example.architecture.homework2_withoutshiro.models;

import java.util.Arrays;
import java.util.List;

public enum OperationType {
    INSERT,
    DELETE,
    UPDATE,
    SEARCH;

    private static List<OperationType> RESOURCETYPE_ORDER = Arrays.asList(INSERT, DELETE, UPDATE, SEARCH);

    public static OperationType valueOfStr(String str) {
        return OperationType.valueOf(str.toUpperCase());
    }
}
