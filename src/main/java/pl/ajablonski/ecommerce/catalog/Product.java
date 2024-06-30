package pl.ajablonski.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageName;

    public Product(UUID id, String name, String description, BigDecimal price) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void changePrice(BigDecimal price) {
        this.price = price;
    }

    public void assignImage(String imageName) {
        this.imageName = imageName;
    }

    public String getImage() {
        return this.imageName;
    }


    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}