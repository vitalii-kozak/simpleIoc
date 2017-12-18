package pizza.domain.service;

import pizza.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order saveOrder(Order order);
}
