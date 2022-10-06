package com.cydeo.spring05thymeleaf.repository.impl;

import com.cydeo.spring05thymeleaf.model.Cart;
import com.cydeo.spring05thymeleaf.model.CartItem;
import com.cydeo.spring05thymeleaf.model.Product;
import com.cydeo.spring05thymeleaf.repository.CartRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CartRepositoryImpl implements CartRepository {
    public  Cart cart;


    @Override
    public boolean addProduct(Product product) {
        CartItem cartItem = new CartItem(product, product.getQuantity(),
                product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())));

        return cart.getCartItemList().add(cartItem);
    }



    @Override
    public Cart displayCart() {
        return cart;
    }
}
