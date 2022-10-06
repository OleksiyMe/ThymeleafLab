package com.cydeo.spring05thymeleaf.repository;

import com.cydeo.spring05thymeleaf.model.Cart;
import com.cydeo.spring05thymeleaf.model.Product;


public interface CartRepository {

    boolean addProduct(Product product);

    Cart displayCart();
}



