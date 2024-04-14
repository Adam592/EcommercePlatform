package pl.ajablonski.ecommerce.catalog;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import  static org.assertj.core.api.Assertions.*;

public class HashMapProductStorageTest {
    @Test
    void itAllowsToStoreProduct(){
        Product product = thereIsExampleProduct();
        ProductStorage hashmapStorage = thereIsHashMapStorage();
        hashmapStorage.add(product);
        List<Product> products = hashmapStorage.allProducts();
        assertThat(products).hasSize(1).extracting(Product::getName).contains("test-it");
    }

    private static Product thereIsExampleProduct() {
        return new Product(UUID.randomUUID(), "test-it", "test");
    }

    private ProductStorage thereIsHashMapStorage() {
        return new HashMapProductStorage();
    }

    @Test
    void itAllowsToLoadAllProducts(){

    }

    @Test
    void itAllowsToLoadProductById(){

    }

}
