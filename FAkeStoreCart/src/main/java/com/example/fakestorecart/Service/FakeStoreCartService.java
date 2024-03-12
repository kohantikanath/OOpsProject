package com.example.fakestorecart.Service;

import com.example.fakestorecart.DTOS.FakeStoreCartServiceDto;
import com.example.fakestorecart.Models.Cart;
import com.example.fakestorecart.Models.Products;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FakeStoreCartService {
    private RestTemplate restTemplate = new RestTemplate();

    public Cart getSingleCart(Long id) {
        FakeStoreCartServiceDto fakeStoreCartServiceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/" + id,
                FakeStoreCartServiceDto.class
        );
        Cart cart = new Cart();
        cart.setId(fakeStoreCartServiceDto.getId());
        cart.setUserId(fakeStoreCartServiceDto.getUserId());
        cart.setDate(fakeStoreCartServiceDto.getDate());
        List<Products> products = new ArrayList<>();
        for (HashMap<String, Integer> ele : fakeStoreCartServiceDto.getProducts()) {
            Products product = new Products();
            product.setProductId(ele.get("productId"));
            product.setQuantity(ele.get("quantity"));
            products.add(product);
        }
        cart.setProducts(products);
        return cart;
    }

    public List<Cart> getAllCarts() {
        FakeStoreCartServiceDto[] fakeStoreCartServiceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/",
                FakeStoreCartServiceDto[].class
        );
        List<Cart> ans = new ArrayList<>();
        for (FakeStoreCartServiceDto carts : fakeStoreCartServiceDto) {
            Cart cart = new Cart();
            cart.setId(carts.getId());
            cart.setUserId(carts.getUserId());
            cart.setDate(carts.getDate());
            List<Products> products = new ArrayList<>();
            for (HashMap<String, Integer> ele : carts.getProducts()) {
                Products product = new Products();
                product.setProductId(ele.get("productId"));
                product.setQuantity(ele.get("quantity"));
                products.add(product);
            }
            cart.setProducts(products);
            ans.add(cart);
        }
        return ans;
    }
    }
