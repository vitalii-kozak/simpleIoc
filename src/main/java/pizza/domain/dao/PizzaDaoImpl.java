package pizza.domain.dao;

import pizza.domain.Pizza;
import pizza.domain.PizzaType;
import pizza.domain.repo.PizzaRepo;

import java.util.ArrayList;
import java.util.List;

public class PizzaDaoImpl implements PizzaDao{

    private PizzaRepo pizzaRepo;

    public PizzaDaoImpl(PizzaRepo pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepo.getPizzas();
    }

    @Override
    public List<Pizza> getAllByType(PizzaType pizzaType) {
        List<Pizza> pizzaNewList = new ArrayList<>();
        for (Pizza pizza : pizzaRepo.getPizzas()){
            if(pizza.getPizzaType() == pizzaType) {
                pizzaNewList.add(pizza);
            }
        }
        return pizzaNewList;
    }

    @Override
    public List<Pizza> getAllByName(String pizzaName) {
        List<Pizza> pizzaNewList = new ArrayList<>();
        for (Pizza pizza : pizzaRepo.getPizzas()){
            if(pizza.getTitle().equals(pizzaName)) {
                pizzaNewList.add(pizza);
            }
        }
        return pizzaNewList;
    }
}
