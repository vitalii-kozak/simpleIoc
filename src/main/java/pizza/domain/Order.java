package pizza.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private User user;
    private double totalPrice;
    private List<Pizza> pizzasList;

    public Order(int id, User user) {
        this.id = id;
        this.user = user;
        pizzasList = new ArrayList<>();
    }

    public Order add(Pizza pizza) {
        if(pizza == null) {
            return null;
        }
        pizzasList.add(pizza);
        totalPrice += pizza.getPrice();
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Pizza> getPizzasList() {
        return pizzasList;
    }

    public void setPizzasList(List<Pizza> pizzasList) {
        this.pizzasList = pizzasList;
    }
}