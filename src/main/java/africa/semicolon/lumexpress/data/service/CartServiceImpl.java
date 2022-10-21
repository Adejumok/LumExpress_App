package africa.semicolon.lumexpress.data.service;

import africa.semicolon.lumexpress.data.dto.request.CartRequest;
import africa.semicolon.lumexpress.data.dto.response.CartResponse;
import africa.semicolon.lumexpress.data.models.Cart;
import africa.semicolon.lumexpress.data.models.Item;
import africa.semicolon.lumexpress.data.models.Product;
import africa.semicolon.lumexpress.data.repositories.CartRepository;
import africa.semicolon.lumexpress.exception.CartNotFoundException;
import africa.semicolon.lumexpress.exception.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final ProductService productService;
    private Cart cart;

    @Override
    public CartResponse addProductToCart(CartRequest cartRequest) throws CartNotFoundException, ProductNotFoundException {
        cartRepository.findById(cartRequest.getCartId())
                .orElseThrow(() -> new CartNotFoundException("cart with id %d not found", cartRequest.getCartId()));
        Product foundProduct = productService.getProductById(cartRequest.getCartId());
        Item item = buildCartItem(foundProduct);
        cart.getItems().add(item);
        Cart savedCart = cartRepository.save(updateCartSubTotal(cart, item));

        return CartResponse.builder()
                .message("items added to cart")
                .cart(savedCart)
                .build();
    }

    private Cart updateCartSubTotal(Cart cart, Item item){
        cart.setSubTotal(cart.getSubTotal()
                .add(item.getProduct().getPrice()));
        return cart;
    }

    private Item buildCartItem(Product foundProduct) {
        return Item.builder()
                .product(foundProduct).build();
    }

    public List<Cart> getCartList(){
        return cartRepository.findAll();
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
