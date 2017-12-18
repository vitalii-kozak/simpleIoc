package pizza.domain.dao;

import pizza.domain.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getAllOrders();
    Order saveOrder(Order order);
}
