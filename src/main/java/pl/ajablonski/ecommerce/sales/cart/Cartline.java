package pl.ajablonski.ecommerce.sales.cart;

public class Cartline {

    String productId;
    int qty;
    public Cartline(String productId, int qty){
        this.productId = productId;
        this.qty = qty;
    }

    public Object getProductId() {
        return null;
    }

    public int getQuantity() {
        return 0;
    }
}
