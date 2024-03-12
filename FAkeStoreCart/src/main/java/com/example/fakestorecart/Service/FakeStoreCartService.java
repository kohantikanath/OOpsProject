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
public class FakeStoreCartService implements CartService {

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
        System.out.println("Cart fetched successfully!!!");
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
        System.out.println("All carts fetched successfully!!!");
        return ans;
    }

    public List<Cart> getCartsInDateRange(String startDate, String endDate) {
        FakeStoreCartServiceDto[] fakeStoreCartServiceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts?startdate="+startDate+"&enddate="+endDate,
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
        System.out.println("All carts in date range "+startDate+" to "+endDate+" fetched successfully!!!");
        return ans;
    }

    public List<Cart> getUserCarts(Long id) {
        FakeStoreCartServiceDto[] fakeStoreCartServiceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/user/" + id,
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
        System.out.println("User's cart fetched successfully!!!");
        return ans;
    }

    public void deleteCart(Long id) {
        restTemplate.delete("https://fakestoreapi.com/carts/" + id);
        System.out.println("Cart deleted successfully!!!");
    }

    public Cart createCart(Cart cart) {
        FakeStoreCartServiceDto storeCart = new FakeStoreCartServiceDto();
        storeCart.setId(cart.getId());
        storeCart.setUserId(cart.getUserId());
        storeCart.setDate(cart.getDate());
        HashMap<String, Integer>[] products = new HashMap[cart.getProducts().size()];
        for (int i = 0; i < cart.getProducts().size(); i++) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("productId", (int)cart.getProducts().get(i).getProductId());
            map.put("quantity", (int)cart.getProducts().get(i).getQuantity());
            products[i] = map;
        }

        restTemplate.postForObject(
                "https://fakestoreapi.com/carts",
                storeCart,
                FakeStoreCartServiceDto.class
        );
        System.out.println("Cart created successfully!!!");
        return cart;
    }

    public Cart updateCart(Cart cart) {
        FakeStoreCartServiceDto storeCart = new FakeStoreCartServiceDto();
        storeCart.setId(cart.getId());
        storeCart.setUserId(cart.getUserId());
        storeCart.setDate(cart.getDate());
        HashMap<String, Integer>[] products = new HashMap[cart.getProducts().size()];
        for (int i = 0; i < cart.getProducts().size(); i++) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("productId", (int)cart.getProducts().get(i).getProductId());
            map.put("quantity", (int)cart.getProducts().get(i).getQuantity());
            products[i] = map;
        }

        restTemplate.put(
                "https://fakestoreapi.com/carts/" + cart.getId(),
                storeCart
        );
        System.out.println("Cart updated successfully!!!");
        return cart;
    }

}
