package pl.ajablonski.ecommerce.playground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import pl.ajablonski.ecommerce.catalog.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class JdbcPlaygroundTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setupDatabase() {
        var dropSql = "DROP TABLE `product_catalog__products` IF EXISTS";
        jdbcTemplate.execute(dropSql);

        var sql = """
            CREATE TABLE `product_catalog__products` (
                id VARCHAR(100) NOT NULL,
                name VARCHAR(100) NOT NULL,
                description VARCHAR(100) NOT NULL,
                price DECIMAL(12,2),
                cover VARCHAR(100),
                PRIMARY KEY(id)
            );
        """;
        jdbcTemplate.execute(sql);
    }

    @Test
    void selectMyNameViaDB(){
        var name = jdbcTemplate.queryForObject(
                "select now() my_date",
                String.class
        );

        assert name.contains("2024");
    }

    @Test
    void itCreatesTable(){
        var countSql = "select count(*) from `product_catalog__products`;";
        var results = jdbcTemplate.queryForObject(countSql, Integer.class);
        assertThat(results).isEqualTo(0);
    }

    @Test
    void itStoreProducts() {
        var myInsertSql = """
                INSERT INTO `product_catalog__products` (id, name, price)
                VALUES
                    ('product_id_1', 'My lego set', 20.20),
                    ('product_id_2', 'My cobi set', 10.20)
                ;
             """;
        jdbcTemplate.execute(myInsertSql);


        var countSql = "select count(*) from `product_catalog__products`";
        var results = jdbcTemplate.queryForObject(countSql, Integer.class);

        assertThat(results).isEqualTo(2);
    }

    @Test
    void itStoreDynamicProducts(){
        var product = new Product(UUID.randomUUID(), "My lego set", "Nice one");
        product.changePrice(BigDecimal.valueOf((10.10)));
        var myInsertSql = """
            INSERT INTO `product_catalog__products` (id, name, price)
            VALUES
                (?, ?, ?)
            ;
        """;
        jdbcTemplate.update(myInsertSql, product.getId(), product.getName(), product.getPrice());

        var countSql = "select count(*) from `product_catalog__products`";
        var results = jdbcTemplate.queryForObject(countSql,Integer.class);

        assertThat(results).isEqualTo(1);
    }
    @Test
    void loadProductById() {
        var product = new Product(UUID.randomUUID(), "My lego set", "Nice one");
        product.changePrice(BigDecimal.valueOf((10.10)));

        var myInsertSql = """
                    INSERT INTO `product_catalog__products` (id, name, price)
                    VALUES
                        (?, ?, ?)
                    ;
                """;
        jdbcTemplate.update(myInsertSql, product.getId(), product.getName(), product.getPrice());

        var productId = product.getId();
        var selectProductSql = "select * from `product_catalog__products` where id = ?";
        Product loadedProduct = jdbcTemplate.queryForObject(
                selectProductSql,
                new Object[]{productId},
                (rs, i) -> {
                    var myProduct = new Product(
                            UUID.fromString(rs.getString("id")),
                            rs.getString("name"),
                            rs.getString("name")
                    );
                    myProduct.changePrice(BigDecimal.valueOf(rs.getDouble("price")));
                    return myProduct;
                }
        );
    }

    @Test
    void itLoadsAllProductsAtOnce(){
        var myInsertSql = """
                    INSERT INTO `product_catalog__products` (id, name, price)
                    VALUES
                        ('product_id_1', 'My lego set', 20.20),
                        ('product_id_2', 'My lego set', 10.20)
                    ;
                """;
        jdbcTemplate.execute(myInsertSql);
        List<Map<String, Object>> products = jdbcTemplate.queryForList("select * from `product_catalog__products`");
    }
}
