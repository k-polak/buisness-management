package com.buisness.management;

import com.buisness.management.daos.AddressDao;
import com.buisness.management.daos.ClientDao;
import com.buisness.management.daos.EmployeeDao;
import com.buisness.management.daos.OrderDao;
import com.buisness.management.daos.OrderProductDao;
import com.buisness.management.daos.ProductDao;
import com.buisness.management.db.DatabaseAccess;
import com.buisness.management.db.MySqlDatabaseAccess;
import com.buisness.management.model.Order;

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

    public ClientDao getClientDao(){
        return new ClientDao(databaseAccess, this);
    }

    public ProductDao getProductDao(){
        return new ProductDao(databaseAccess);
    }

    public OrderDao getOrderDao(){
        return new OrderDao(databaseAccess, this);
    }

    public OrderProductDao getOrderProductDao(){
        return new OrderProductDao(databaseAccess);
    }
}
