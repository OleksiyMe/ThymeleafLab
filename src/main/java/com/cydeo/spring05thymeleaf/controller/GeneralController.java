package com.cydeo.spring05thymeleaf.controller;

import com.cydeo.spring05thymeleaf.model.Product;
import com.cydeo.spring05thymeleaf.service.CartService;
import com.cydeo.spring05thymeleaf.service.ProductService;
import com.cydeo.spring05thymeleaf.service.impl.CartServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller

public class GeneralController {

    private final ProductService productService;
    private final CartService cartService;

    public GeneralController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/list")
    public String listProduct(Model model) {

        model.addAttribute("products", productService.listProduct());
        return "/product/list";
    }

    @GetMapping("/create-product")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/product/create-product";
    }

    @PostMapping("/123")
    public String saveProduct(@ModelAttribute("product") Product product) {

        product.setId(UUID.randomUUID());
        productService.productCreate(product);
        return "redirect:/list";
    }

    @GetMapping("/cart/{id}/{quantity}")
    public String addProductToCart(@PathVariable("id") UUID id,
                                   @PathVariable("quantity") String quantityString,    Model model) {
        cartService.addToCart(UUID.fromString(id.toString()) , Integer.valueOf(quantityString));
        model.addAttribute("cart", CartServiceImpl.CART);
        model.addAttribute("cartItems", CartServiceImpl.CART.getCartItemList());


        return "/cart/show-cart";
    }

    @GetMapping("/cart")
    public String addProductToCart( Model model) {

        model.addAttribute("cart", CartServiceImpl.CART);
        model.addAttribute("cartItems", CartServiceImpl.CART.getCartItemList());


        return "/cart/show-cart";
    }
    @GetMapping("/delete-from-cart/{id}")
    public String deleteCartItem(@PathVariable("id") UUID id) {

        cartService.deleteFromCart(id);

        return "redirect:/cart";
    }


}
