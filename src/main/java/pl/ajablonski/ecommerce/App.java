package pl.ajablonski.ecommerce;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.ajablonski.ecommerce.catalog.ArrayListProductStorage;
import pl.ajablonski.ecommerce.catalog.ProductCatalog;
import pl.ajablonski.ecommerce.sales.cart.InMemoryCartStorage;
import pl.ajablonski.ecommerce.sales.PayUPaymentGateway;
import pl.ajablonski.ecommerce.sales.SalesFacade;
import pl.ajablonski.ecommerce.sales.offering.OfferCalculator;
import pl.ajablonski.ecommerce.sales.reservation.ReservationRepository;

@SpringBootApplication
public class App {
    public static void main(String[] args){
        System.out.println("A");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyProductCatalog(){
        ProductCatalog productCatalog = new ProductCatalog(new ArrayListProductStorage());
        productCatalog.addProduct("Lego set 1", "Nice one");
        productCatalog.addProduct("Cobi set 2", "Nice one");
        return productCatalog;
    }

    @Bean
    SalesFacade createMySalesFacade(){
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(),
                new PayUPaymentGateway(),
                new ReservationRepository()
                );
    }
}
