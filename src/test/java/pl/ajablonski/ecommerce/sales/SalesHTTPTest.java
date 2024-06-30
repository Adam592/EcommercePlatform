package pl.ajablonski.ecommerce.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.ajablonski.ecommerce.catalog.ProductCatalog;
import pl.ajablonski.ecommerce.sales.offering.AcceptOfferRequest;
import pl.ajablonski.ecommerce.sales.reservation.ReservationDetail;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest(
        webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class SalesHTTPTest {
    @Autowired
    TestRestTemplate http;

    @LocalServerPort
    private int port;

    @Autowired
    ProductCatalog catalog;

    @Test
    void itAllowToAcceptOffer(){
        //ARRANGE
        String productId = thereIsExampleProduct("Example Product", BigDecimal.valueOf(10.10));

        //ACT
        //add product to cart
        String addProductToCartURL = String.format("http://localhost:%s/%s/%s", port, "api/add-to-cart/", productId);
        ResponseEntity<Object> addProductResponse = http.postForEntity(addProductToCartURL, null, Object.class);

        AcceptOfferRequest acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFirstName("Adam")
                .setLastName("Jabłoński")
                .setEmail("adam.jablonski@example.com");

        String acceptOfferURL = String.format("http://localhost:%s/%s", port, "api/accept-offer/");
        ResponseEntity<ReservationDetail> reservationDetailResponseEntity = http.postForEntity(acceptOfferURL,acceptOfferRequest, ReservationDetail.class);

        assertThat(reservationDetailResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(reservationDetailResponseEntity.getBody().getReservationId());
        assertNotNull(reservationDetailResponseEntity.getBody().getPaymentUrl());
        assertEquals(BigDecimal.valueOf(10.10), reservationDetailResponseEntity.getBody().getTotal());
    }

    private String thereIsExampleProduct(String name, BigDecimal price){
        var id = catalog.addProduct(name, name, price);
        catalog.changePrice(id, price);
        return id;
    }
}
