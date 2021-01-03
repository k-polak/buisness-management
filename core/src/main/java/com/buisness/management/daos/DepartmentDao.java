package com.buisness.management.daos;

import com.buisness.management.db.DatabaseAccess;

public class DepartmentDao {
    private final DatabaseAccess databaseAccess;

    public DepartmentDao(DatabaseAccess databaseAccess) {
        this.databaseAccess = databaseAccess;
    }
}
