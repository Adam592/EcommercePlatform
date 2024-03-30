package pl.ajablonski.creditcard;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
public class AssertjTest {

    @Test
    void moreExpressiveAssertions(){
        var hello = "Hello World";
        assertThat(hello).contains("hello");
        assertThat(new ArrayList<>())
                .hasSize(10)
                .contains("Hello");
    }
}
