package pizza.domain;

import java.util.Comparator;
import java.util.Objects;

public class Pizza{

    private int id;
    private String title;
    private double price;
    private PizzaType pizzaType;


    public Pizza(int id, String title, double price, PizzaType pizzaType) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.pizzaType = pizzaType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PizzaType getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return id == pizza.id &&
                Double.compare(pizza.price, price) == 0 &&
                Objects.equals(title, pizza.title) &&
                pizzaType == pizza.pizzaType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", pizzaType=" + pizzaType +
                '}';
    }

    public static final Comparator<Pizza> COMPARE_BY_NAME = new Comparator<Pizza>() {
        @Override
        public int compare(Pizza pizza1, Pizza pizza2) {
            return pizza1.getTitle().compareTo(pizza2.getTitle());
        }
    };

    public static final Comparator<Pizza> COMPARE_BY_TYPE = new Comparator<Pizza>() {
        @Override
        public int compare(Pizza pizza1, Pizza pizza2) {
            return pizza1.getPizzaType().compareTo(pizza2.getPizzaType());
        }
    };
}
