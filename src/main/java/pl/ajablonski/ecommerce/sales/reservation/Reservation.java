package pl.ajablonski.ecommerce.sales.reservation;

import pl.ajablonski.ecommerce.sales.PaymentDetails;
import pl.ajablonski.ecommerce.sales.offering.AcceptOfferRequest;

import java.math.BigDecimal;

public class Reservation {

    private String reservationId;

    public Reservation(String reservationId){
        this.reservationId = reservationId;
    }
    public static Reservation of(String reservationId, String customerId, AcceptOfferRequest acceptOfferRequest, PaymentDetails paymentDetails) {
        return new Reservation(reservationId);
    }

    public boolean isPending() {
        return true;
    }

    public CustomerDetails getCustomerDetails() {
        return null;
    }

    public BigDecimal getTotal() {
        return null;
    }

    public String getId() {
        return reservationId;
    }
}
