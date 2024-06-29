package pl.ajablonski.ecommerce.sales;

import pl.ajablonski.ecommerce.sales.cart.Cart;
import pl.ajablonski.ecommerce.sales.cart.InMemoryCartStorage;
import pl.ajablonski.ecommerce.sales.offering.AcceptOfferRequest;
import pl.ajablonski.ecommerce.sales.offering.Offer;
import pl.ajablonski.ecommerce.sales.offering.OfferCalculator;
import pl.ajablonski.ecommerce.sales.payment.PaymentDetails;
import pl.ajablonski.ecommerce.sales.payment.PaymentGateway;
import pl.ajablonski.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.ajablonski.ecommerce.sales.reservation.Reservation;
import pl.ajablonski.ecommerce.sales.reservation.ReservationDetail;
import pl.ajablonski.ecommerce.sales.reservation.ReservationRepository;

import java.util.UUID;

public class SalesFacade {
    private InMemoryCartStorage cartStorage;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(
            InMemoryCartStorage cartStorage,
            OfferCalculator offerCalculator,
            PaymentGateway paymentGateway,
            ReservationRepository reservationRepository
    ){
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRepository;
    }

    public Offer getCurrentOffer(String customerId) {
        return new Offer();
    }

    public ReservationDetail acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(acceptOfferRequest, offer.getTotal())
        );
        Reservation reservation = Reservation.of(reservationId, customerId, acceptOfferRequest, paymentDetails);

        reservationRepository.add(reservation);

        return new ReservationDetail(reservationId, paymentDetails.getPaymentUrl());
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);
        cart.addProduct(productId);
        cartStorage.save(customerId, cart);
    }

    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomer(customerId).orElse(Cart.empty());
    }
}
