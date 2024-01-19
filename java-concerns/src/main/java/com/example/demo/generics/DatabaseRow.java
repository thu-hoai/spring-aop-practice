package com.example.demo.generics;

import java.util.HashMap;
import java.util.Map;

public class DatabaseRow {

    private Map<Column<?>, Object> columns = new HashMap<>();


    // Given

    // When Then
    public <T> void putColumn(Column<T> type, T instance) {
        if (type == null) {
            throw new NullPointerException("Type is null");
        }
        columns.put(type, instance.getClass().cast(instance));
    }

    public <T> T getColumn(Column<T> type) {
        return type.cast(columns.get(type));
    }

    public static void main(String[] args) {
        Column<Integer> integerColumn = new Column<>(Integer.class);
        Column<String> stringColumn = new Column<>(String.class);

        DatabaseRow databaseRow = new DatabaseRow();

        databaseRow.putColumn(integerColumn, 3);

        databaseRow.putColumn(integerColumn, 4);

        databaseRow.putColumn(stringColumn, "3");

        databaseRow.getColumn(integerColumn);
    }
}
