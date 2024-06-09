package pl.ajablonski.ecommerce.sales.reservation;

import pl.ajablonski.ecommerce.sales.PaymentDetails;
import pl.ajablonski.ecommerce.sales.PaymentGateway;
import pl.ajablonski.ecommerce.sales.RegisterPaymentRequest;

public class SpyPaymentGateway implements PaymentGateway {

    Integer requestCount = 0;
    public RegisterPaymentRequest lastRequest;
    public Integer getRequestCount() {
        return requestCount;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest){
        this.requestCount++;
        lastRequest = registerPaymentRequest;
        return new PaymentDetails("http://spy-gateway");
    }
}
