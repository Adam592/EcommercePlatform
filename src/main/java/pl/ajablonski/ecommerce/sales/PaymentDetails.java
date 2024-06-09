package pl.ajablonski.ecommerce.sales;

public class PaymentDetails {
    private final String url;

    public PaymentDetails(String url) {

        this.url = url;
    }

    public String getPaymentUrl() {
        return url;
    }
}
