package com.example.fakestorecart.Service;
import com.example.fakestorecart.Models.Cart;

import java.util.List;
public interface CartService {
    public Cart getSingleCart(Long id);

    public List<Cart> getAllCarts();

    public List<Cart> getCartsInDateRange (String startDate, String endDate);

    public List<Cart> getUserCarts(Long id);

    public void deleteCart(Long id);

    public Cart createCart(Cart cart);

    public Cart updateCart(Cart cart);
}
