package pl.ajablonski.ecommerce.catalog;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalogTest {

    @Test
    void itAllowsListingProducts(){
        ProductCatalog catalog = thereIsProductCatalog();
        List<Product> products = catalog.allProducts();

        assert products.isEmpty();
    }

    @Test
    void itAllowsToAddProduct(){
        ProductCatalog catalog = thereIsProductCatalog();
        catalog.addProduct("Legoset 8083", "nice one", BigDecimal.valueOf(10));
        List<Product> allProducts = catalog.allProducts();
        assertThat(allProducts).hasSize(1);
    }

    @Test
    void itLoadsSingleProductById(){
        ProductCatalog catalog = thereIsProductCatalog();
        String id = catalog.addProduct("Legoset 8083", "nice one", BigDecimal.valueOf(10));
        Product loaded = catalog.getProductBy(id);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void itAllowsToChangePrice(){
        ProductCatalog catalog = thereIsProductCatalog();
        String id = catalog.addProduct("Legoset 8083", "nice one", BigDecimal.valueOf(10));

        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductBy(id);
        assertThat(loaded.getPrice()).isEqualTo(BigDecimal.valueOf(10.10));
    }
    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(new ArrayListProductStorage());
    }

    @Test
    void itAllowsToAssignImage(){
        ProductCatalog catalog = thereIsProductCatalog();
        String id = catalog.addProduct("Legoset 8083", "nice one", BigDecimal.valueOf(10));

        catalog.assignImage(id, "legoset8083.png");
        Product loaded = catalog.getProductBy(id);
        assertThat(loaded.getImage()).isEqualTo("legoset8083.png");
    }

}
