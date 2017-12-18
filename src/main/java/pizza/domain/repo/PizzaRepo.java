package pizza.domain.repo;

import pizza.domain.Pizza;
import pizza.domain.PizzaType;

import java.util.ArrayList;
import java.util.List;

public class PizzaRepo {
    private List<Pizza> pizzas = new ArrayList<Pizza>(){{
        add(new Pizza(1, "Pizza1", 100, PizzaType.PIZZA_ONE));
        add(new Pizza(1, "Pizza2", 110, PizzaType.PIZZA_TWO));
        add(new Pizza(1, "Pizza3", 120, PizzaType.PIZZA_THREE));
        add(new Pizza(1, "Pizza4", 105, PizzaType.PIZZA_THREE));
        add(new Pizza(1, "Pizza5", 110, PizzaType.PIZZA_ONE));
    }};

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public int getLastPizzaId(){
        return (pizzas.size()==0) ? 0 : pizzas.get(pizzas.size()-1).getId();
    }

}
