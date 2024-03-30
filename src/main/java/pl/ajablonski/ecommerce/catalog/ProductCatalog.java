package pl.ajablonski.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {

    ArrayList<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    public List<Product> allProducts() {
        return Collections.unmodifiableList(products);
    }

    public String addProduct(String name, String description) {
        UUID id = UUID.randomUUID();
        Product newProduct = new Product(id, name, description);

        products.add(newProduct);
        return id.toString();
    }

    public Product getProductBy(String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .get();
    }

    public void changePrice(String id, BigDecimal price) {
        Product loadedProduct = getProductBy(id);
        loadedProduct.changePrice(price);
    }

    public void assignImage(String id, String imageName) {
        Product loadedProduct = getProductBy(id);
        loadedProduct.assignImage(imageName);
    }
}
