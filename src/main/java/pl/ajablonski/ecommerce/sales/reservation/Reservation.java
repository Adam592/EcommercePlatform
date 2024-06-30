package pl.ajablonski.ecommerce.sales.reservation;

import pl.ajablonski.ecommerce.sales.offering.Offer;
import pl.ajablonski.ecommerce.sales.payment.PaymentDetails;
import pl.ajablonski.ecommerce.sales.offering.AcceptOfferRequest;

import java.math.BigDecimal;

public class Reservation {

    private String reservationId;
    private CustomerDetails customerDetails;

    public Reservation(String reservationId, CustomerDetails customerDetails){
        this.reservationId = reservationId;
        this.customerDetails = customerDetails;
    }
    public static Reservation of(String reservationId, String customerId, AcceptOfferRequest acceptOfferRequest, PaymentDetails paymentDetails) {
        return new Reservation(
                reservationId,
                new CustomerDetails(customerId, acceptOfferRequest.getFirstName(), acceptOfferRequest.getLastName(), acceptOfferRequest.getEmail())
        );
    }

    public boolean isPending() {
        return true;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public BigDecimal getTotal() {
        return null;
    }

    public String getId() {
        return reservationId;
    }
}
