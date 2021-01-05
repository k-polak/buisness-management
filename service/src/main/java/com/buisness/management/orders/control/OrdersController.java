package com.buisness.management.orders.control;

import com.buisness.management.DataManager;
import com.buisness.management.DtoMapper;
import com.buisness.management.dtos.ClientDTO;
import com.buisness.management.dtos.OrderDTO;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
public class OrdersController {
    DataManager dataManager = new DataManager();

    public List<OrderDTO> getAllOrders(){
        return dataManager.getOrderDao().findAll()
                .stream()
                .map(DtoMapper::mapToOrderDTO)
                .collect(Collectors.toList());

    }

    public void createOrder(OrderDTO orderDTO){
        dataManager.getOrderDao().create(DtoMapper.mapToOrder(orderDTO));
    }
}
