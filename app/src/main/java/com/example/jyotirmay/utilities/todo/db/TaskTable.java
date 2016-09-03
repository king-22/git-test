package com.example.jyotirmay.utilities.todo.db;

/**
 * Created by jyotirmay on 25-08-2016.
 */

public class TaskTable extends DbTable {


    public static final String TABLE_NAME = "tasks";

    public interface Columns {
        String NAME = "name";
        String DATE = "date";
        String DONE = "done";
    }

    public static final String TABLE_CREATE_CMD =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                    + LBR +
                        Columns.DATE +TYPE_INT + COMMA +
                        Columns.NAME + TYPE_TEXT + COMMA +
                        Columns.DONE + TYPE_INT +
                        RBR + ";";
}

