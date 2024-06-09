package pl.ajablonski.ecommerce.sales;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ajablonski.ecommerce.sales.offering.AcceptOfferRequest;
import pl.ajablonski.ecommerce.sales.offering.Offer;
import pl.ajablonski.ecommerce.sales.reservation.ReservationDetail;

@RestController
public class SalesController {
    SalesFacade salesFacade;

    public SalesController(SalesFacade salesFacade){
        this.salesFacade = salesFacade;
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer(){
        String customerId = getCurrentCustomerId();
        return salesFacade.getCurrentOffer(customerId);
    }

    @PostMapping("/api/accept-offer")
    ReservationDetail acceptOffer(AcceptOfferRequest acceptOfferRequest){
        String customerId = getCurrentCustomerId();
        ReservationDetail reservationDetail = salesFacade.acceptOffer(customerId, acceptOfferRequest);

        return reservationDetail;
    }
    @PostMapping("/api/add-to-cart/{productId}")
    void addToCart(@PathVariable String productId){
        String customerId = getCurrentCustomerId();
        salesFacade.addToCart(customerId, productId);
    }

    private String getCurrentCustomerId() {
        return "Adam";
    }
}
