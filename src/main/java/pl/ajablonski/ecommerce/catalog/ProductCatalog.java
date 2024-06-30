package pl.ajablonski.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {

    ProductStorage productStorage;

    public ProductCatalog(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public List<Product> allProducts() {
        return productStorage.allProducts();
    }

    public String addProduct(String name, String description, BigDecimal price) {
        UUID id = UUID.randomUUID();
        Product newProduct = new Product(id, name, description, price);

        productStorage.add(newProduct);
        return id.toString();
    }

    public Product getProductBy(String id) {
        return productStorage.getProductBy(id);
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
