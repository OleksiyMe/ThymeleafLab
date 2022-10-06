package com.cydeo.spring05thymeleaf.service.impl;

import com.cydeo.spring05thymeleaf.model.Cart;
import com.cydeo.spring05thymeleaf.model.CartItem;
import com.cydeo.spring05thymeleaf.model.Product;
import com.cydeo.spring05thymeleaf.service.CartService;
import com.cydeo.spring05thymeleaf.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    public static Cart CART = new Cart(BigDecimal.ZERO, new ArrayList<>());

    private final ProductService productService;

    public CartServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Cart addToCart(UUID productId, Integer quantity) {
        //todo retrieve product from repository method

        //todo initialise cart item
        //todo calculate cart total amount
        //todo add to cart
        Product product = productService.listProduct().stream()
                .filter(p -> p.getId().equals(productId)).findFirst().get();
        CartItem cartItem = new CartItem(product, quantity,
                BigDecimal.valueOf(quantity).multiply(product.getPrice()));
        CART.getCartItemList().add(cartItem);
        CART.getCartTotalAmount().add(cartItem.getTotalAmount());


        return CART;
    }

    @Override
    public boolean deleteFromCart(UUID productId) {
        //todo delete product object from cart using stream
        CART.getCartItemList().remove(
              productService.listProduct().stream().filter(p->productId.equals(productId))
                      .findFirst().get()
        );

        return true;
    }
}
