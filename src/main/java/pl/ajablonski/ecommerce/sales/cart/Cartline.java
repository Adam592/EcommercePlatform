package pl.ajablonski.ecommerce.sales.cart;

public class Cartline {

    String productId;
    int qty;
    public Cartline(String productId, int qty){
        this.productId = productId;
        this.qty = qty;
    }

    public Object getProductId() {
        return productId;
    }

    public int getQuantity() {
        return qty;
    }
}
