package pizza.domain.dao;

import pizza.domain.Pizza;
import pizza.domain.PizzaType;

import java.util.List;

public interface PizzaDao {

    List<Pizza> getAllPizzas();
    List<Pizza> getAllByType(PizzaType pizzaType);
    List<Pizza> getAllByName(String pizzaName);
}
