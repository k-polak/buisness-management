package com.buisness.management;

import com.buisness.management.daos.AddressDao;
import com.buisness.management.daos.EmployeeDao;
import com.buisness.management.db.DatabaseAccess;
import com.buisness.management.db.MySqlDatabaseAccess;

public class DataManager {
    private  DatabaseAccess databaseAccess;

    public DataManager() {
        this.databaseAccess = new MySqlDatabaseAccess();
    }

    public AddressDao getAddressDao(){
        return new AddressDao(databaseAccess);
    }

    public EmployeeDao getEmployeeDao(){
        return new EmployeeDao(databaseAccess, this);
    }
}
