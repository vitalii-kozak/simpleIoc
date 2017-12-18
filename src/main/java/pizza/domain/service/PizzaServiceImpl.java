package pizza.domain.service;

import pizza.domain.Pizza;
import pizza.domain.PizzaType;
import pizza.domain.dao.PizzaDao;
import pizza.domain.dao.PizzaDaoImpl;

import java.util.List;

public class PizzaServiceImpl implements PizzaService{

    private PizzaDao pizzaDao;

    public PizzaServiceImpl(PizzaDao pizzaDao) {
        this.pizzaDao = pizzaDao;
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaDao.getAllPizzas();
    }

    @Override
    public List<Pizza> getAllPizzasByName(String pizzaName) {
        return pizzaDao.getAllByName(pizzaName);
    }

    @Override
    public List<Pizza> getAllPizzasByType(PizzaType pizzaType) {
        return pizzaDao.getAllByType(pizzaType);
    }
}
