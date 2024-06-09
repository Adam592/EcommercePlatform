package pl.ajablonski.ecommerce.sales.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {

    HashMap<String, Integer> productsQuantity;

    public Cart(){
        this.productsQuantity = new HashMap<>();
    }
    public static Cart empty() {
        return new Cart();
    }

    public void addProduct(String productId) {
        if(!isInCart(productId)) {
            putIntoCart(productId);
        } else {
            increaseQuantity(productId);
        }
    }

    private void increaseQuantity(String productId) {
        productsQuantity.put(productId, productsQuantity.get(productId)+1);
    }

    private void putIntoCart(String productId) {
        productsQuantity.put(productId, 1);
    }

    private boolean isInCart(String productId) {
        return productsQuantity.containsKey(productId);
    }

    public boolean isEmpty() {
        return productsQuantity.isEmpty();
    }

    public int getProductsCount() {
        return productsQuantity.values().size();
    }

    public List<Cartline> getLines() {
        return productsQuantity
                .entrySet()
                .stream()
                .map(es -> new Cartline(es.getKey(), es.getValue()))
                .toList();
    }


}
