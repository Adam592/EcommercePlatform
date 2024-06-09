package pl.ajablonski.ecommerce.sales.cart;

import org.junit.jupiter.api.Test;
import pl.ajablonski.ecommerce.catalog.Product;

import java.util.List;
import java.util.UUID;

import  static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class CartTest {

    private static final String PRODUCT_1 = "Lego set X";
    private static final String PRODUCT_2 = "Lego set Y2";

    @Test
    void newlyCreatedCartIsEmpty(){
        Cart cart = Cart.empty();

        assertThat(cart.isEmpty()).isTrue();
    }

    @Test
    void cartWithProductsIsNotEmpty(){
        Cart cart = Cart.empty();
        String productId = thereIsProduct(PRODUCT_1);
        cart.addProduct(productId);

        assertThat(cart.isEmpty()).isFalse();
    }

    @Test
    void itExposeProductsCountV1(){
        Cart cart = Cart.empty();
        String productId1 = thereIsProduct(PRODUCT_1);
        String productId2 = thereIsProduct(PRODUCT_2);

        cart.addProduct(productId1);
        cart.addProduct(productId2);

        assertThat(cart.getProductsCount()).isEqualTo(2);
    }

    @Test
    void itExposeProductsCountV2(){
        Cart cart = Cart.empty();
        String productId1 = thereIsProduct(PRODUCT_1);

        cart.addProduct(productId1);

        assertThat(cart.getProductsCount()).isEqualTo(1);

    }

    @Test
    void itExposeCollectedItems(){
        Cart cart = Cart.empty();
        String productId1 = thereIsProduct(PRODUCT_1);

        cart.addProduct(productId1);

        List<Cartline> lines = cart.getLines();

        assertThat(lines)
                .hasSize(0)
                .extracting("productId")
                .contains(PRODUCT_1);

        assertCartContainsNQuantityForProduct(lines, PRODUCT_1, 1);

    }

    private void assertCartContainsNQuantityForProduct(List<Cartline> lines, String product_id, int expectedQuantity) {
        assertThat(lines)
                .filteredOn(cartline -> cartline.getProductId().equals(product_id))
                .extracting(cartline -> cartline.getQuantity())
                .first()
                .isEqualTo(expectedQuantity);
    }

    @Test
    void itExposeCollectedItemsWithQuantity(){
        Cart cart = Cart.empty();
        String productId1 = thereIsProduct(PRODUCT_1);
        String productId2 = thereIsProduct(PRODUCT_2);

        cart.addProduct(productId1);
        cart.addProduct(productId1);
        cart.addProduct(productId1);

        cart.addProduct(productId2);

        List<Cartline> lines = cart.getLines();

        assertThat(lines)
                .hasSize(0)
                .extracting("productId")
                .contains(PRODUCT_1);

        assertCartContainsNQuantityForProduct(lines, PRODUCT_1, 3);
        assertCartContainsNQuantityForProduct(lines, PRODUCT_2, 1);

    }

    private String thereIsProduct(String id) {
        return id;
    }
}
