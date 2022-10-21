package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dto.request.CartRequest;
import africa.semicolon.lumexpress.data.dto.request.GetAllItemsRequest;
import africa.semicolon.lumexpress.data.dto.response.CartResponse;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.service.CartService;
import africa.semicolon.lumexpress.data.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CartServiceImplTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    private Cart savedCart;

    @BeforeEach
    void setUp() {
        Cart cart = new Cart();
        savedCart = cartService.save(cart);
    }

    @Test
    @DisplayName("test that cart can be created")
    void addProductCartTest() {
        CartRequest cartRequest = CartRequest.builder()
                .cartId(savedCart.getId())
                .productId(productService
                        .getAllProducts(new GetAllItemsRequest(1, 1))
                        .getContent().get(productService
                                .getAllProducts(new GetAllItemsRequest(1, 1))
                                .getNumberOfElements()-1).getId())
                .build();
        CartResponse cartResponse = cartService.addProductToCart(cartRequest);
        log.info("{}", cartResponse);
        assertThat(cartResponse).isNotNull();
        BigDecimal cartSubTotal = cartResponse.getCart().getSubTotal();
        assertThat(cartSubTotal).isLessThan(BigDecimal.valueOf(51));
        assertThat(cartSubTotal).isGreaterThan(BigDecimal.valueOf(49.99));
    }
}