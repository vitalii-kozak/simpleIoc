package pizza.domain;

import ioc.Config;
import ioc.JavaConfig;
import ioc.SimpleIoc;
import pizza.domain.dao.OrderDaoImpl;
import pizza.domain.dao.PizzaDaoImpl;
import pizza.domain.repo.OrderRepo;
import pizza.domain.repo.PizzaRepo;
import pizza.domain.service.OrderService;
import pizza.domain.service.OrderServiceImpl;
import pizza.domain.service.PizzaService;
import pizza.domain.service.PizzaServiceImpl;

import java.util.HashMap;

public class Program {
    public static void main(String[] args) {
        SimpleIoc simpleIoC = buildIoC();
        PizzaService pizzaService = (PizzaService) simpleIoC.getBean("pizzaService");
        OrderService orderService = (OrderService) simpleIoC.getBean("orderService");

        System.out.println(pizzaService.getAllPizzas());

        System.out.println(pizzaService.getAllPizzasByName("Pizza1"));

        System.out.println(pizzaService.getAllPizzasByType(PizzaType.PIZZA_THREE));

//        orderService.placeOrder(new User(2, "Jack", "Jackson"),
//                new Pizza(1, "Hawaii", PizzaType.PIZZA_ONE, 100), new Pizza(2, "Cheese", PizzaType.PIZZA_TWO, 150));

        System.out.println(orderService.getAllOrders());
    }

    private static SimpleIoc buildIoC() {
        Config config = new JavaConfig(new HashMap<String, Class<?>>() {{
            put("orderRepo", OrderRepo.class);
            put("pizzaRepo", PizzaRepo.class);
            put("orderDao", OrderDaoImpl.class);
            put("pizzaDao", PizzaDaoImpl.class);
            put("orderService", OrderServiceImpl.class);
            put("pizzaService", PizzaServiceImpl.class);
        }});
        return new SimpleIoc(config);
    }
}