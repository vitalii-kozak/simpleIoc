package pizza.domain.dao;

import pizza.domain.Order;
import pizza.domain.repo.OrderRepo;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    OrderRepo orderRepo;

    public OrderDaoImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.getOrders();
    }

    @Override
    public Order saveOrder(Order order) {
        orderRepo.addOrder(order);
        return order;
    }
}
