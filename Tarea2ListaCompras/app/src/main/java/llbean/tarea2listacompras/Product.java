package llbean.tarea2listacompras;

/**
 * Created by Mariano on 11/13/2016.
 */

public final class Product {

    private String description;
    private Integer amount;
    private Double price;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
