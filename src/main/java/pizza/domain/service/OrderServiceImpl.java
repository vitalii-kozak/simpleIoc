package pizza.domain.service;

import pizza.domain.Order;
import pizza.domain.dao.OrderDao;
import pizza.domain.repo.OrderRepo;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public Order saveOrder(Order order) {
        return orderDao.saveOrder(order);
    }
}
