package pizza.domain.repo;

import pizza.domain.Order;
import pizza.domain.Pizza;
import pizza.domain.PizzaType;
import pizza.domain.User;

import java.util.ArrayList;
import java.util.List;

public class OrderRepo {

    private List<Order> orders = new ArrayList<Order>() {{
        add(new Order(1, new User(1, "guest")).add(new Pizza(1, "Pizza1", 110, PizzaType.PIZZA_ONE)));
        add(new Order(2, new User(1, "guest")).add(new Pizza(2, "Pizza2", 120, PizzaType.PIZZA_TWO)));
        add(new Order(3, new User(2, "admin")).add(new Pizza(3, "Pizza3", 130, PizzaType.PIZZA_THREE)));
    }};

    public List<Order> getOrders() {
        return orders;
    }

    public int getLastOrderId(){
        return (orders.size()==0) ? 0 : orders.get(orders.size()-1).getId();
    }

    public void addOrder(Order order){
        if (order == null) {
            return;
        }
        orders.add(order);
    }
}
