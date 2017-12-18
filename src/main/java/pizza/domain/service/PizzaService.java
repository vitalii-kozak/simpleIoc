package pizza.domain.service;

import pizza.domain.Pizza;
import pizza.domain.PizzaType;

import java.util.List;

public interface PizzaService {
    List<Pizza> getAllPizzas();
    List<Pizza> getAllPizzasByName(String pizzaName);
    List<Pizza> getAllPizzasByType(PizzaType pizzatype);
}
