package pl.ajablonski.ecommerce.sales;

import org.junit.jupiter.api.Test;
import pl.ajablonski.ecommerce.sales.cart.InMemoryCartStorage;
import pl.ajablonski.ecommerce.sales.offering.Offer;
import pl.ajablonski.ecommerce.sales.offering.OfferCalculator;
import pl.ajablonski.ecommerce.sales.reservation.ReservationRepository;
import pl.ajablonski.ecommerce.sales.reservation.SpyPaymentGateway;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class SalesTest {

    @Test
    void itAddProductToCart(){
        var customerId = thereIsExampleCustomer("Adam");
        var productId = thereIsProduct("Product a", BigDecimal.valueOf(10));

        SalesFacade sales = thereIsSalesFacade();
        sales.addToCart(customerId, productId);

        Offer currentOffer = sales.getCurrentOffer(customerId);
        assertEquals(BigDecimal.valueOf(10), currentOffer.getTotal());
        assertEquals(1, currentOffer.getItemsCount());
    }

    @Test
    void itAddMultipleProductsToCart(){
        var customerId = thereIsExampleCustomer("Adam");
        var productA = thereIsProduct("Product a", BigDecimal.valueOf(10));
        var productB = thereIsProduct("Product b", BigDecimal.valueOf(20));

        SalesFacade sales = thereIsSalesFacade();
        sales.addToCart(customerId, productA);
        sales.addToCart(customerId, productB);

        Offer currentOffer = sales.getCurrentOffer(customerId);
        assertEquals(BigDecimal.valueOf(30), currentOffer.getTotal());
        assertEquals(2, currentOffer.getItemsCount());
    }

    @Test
    void itDoesNotShareCustomersCarts(){
        var customerA = thereIsExampleCustomer("Adam");
        var customerB = thereIsExampleCustomer("Adam2");
        var productA = thereIsProduct("Product a", BigDecimal.valueOf(10));
        var productB = thereIsProduct("Product b", BigDecimal.valueOf(20));
        SalesFacade sales = thereIsSalesFacade();
        
        sales.addToCart(customerA, productA);
        sales.addToCart(customerB, productB);

        Offer currentOfferA = sales.getCurrentOffer(customerA);
        assertEquals(BigDecimal.valueOf(10), currentOfferA.getTotal());

        Offer currentOfferB = sales.getCurrentOffer(customerB);
        assertEquals(BigDecimal.valueOf(20), currentOfferB.getTotal());

    }

    private String thereIsProduct(String name, BigDecimal bigDecimal) {
        return name;
    }

    private SalesFacade thereIsSalesFacade(){
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(),
                new SpyPaymentGateway(),
                new ReservationRepository()
        );
    }

    @Test
    void itShowsCurrentOffer(){
        SalesFacade sales = thereIsSalesFacade();
        var customerId = thereIsExampleCustomer("Adam");

        Offer offer = sales.getCurrentOffer(customerId);
        assertEquals(0, offer.getItemsCount());
        assertEquals(BigDecimal.ZERO, offer.getTotal());

    }

    private String thereIsExampleCustomer(String name) {
        return name;
    }

    @Test
    void itRemoveProductFromCart(){

    }

    @Test
    void itAllowToAcceptOffer(){

    }

    @Test
    void itAllowToPayForReservation(){

    }


}
