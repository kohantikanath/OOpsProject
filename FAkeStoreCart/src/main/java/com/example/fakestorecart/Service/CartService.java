package com.example.fakestorecart.Service;
import com.example.fakestorecart.Models.Cart;

import java.util.List;
public interface CartService {
    Cart getSingleCart(Long id);

    List<Cart> getAllCarts();

    List<Cart> getCartsInDate (String startDate, String endDate);

    List<Cart> getUserCarts(Long id);

    void dCart(Long id);

    Cart createCart(Cart cart);

    Cart uCart(Cart cart);
}
