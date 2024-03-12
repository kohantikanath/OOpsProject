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
        FakeStoreCartServiceDto ServiceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/" + id,
                FakeStoreCartServiceDto.class
        );
        Cart cart = new Cart();
        cart.setId(ServiceDto.getId());
        cart.setUserId(ServiceDto.getUserId());
        cart.setDate(ServiceDto.getDate());
        List<Products> products = new ArrayList<>();
        for (HashMap<String, Integer> ele : ServiceDto.getProducts()) {
            Products product = new Products();
            product.setQuantity(ele.get("quantity"));
            product.setProductId(ele.get("productId"));
            products.add(product);
        }
        cart.setProducts(products);
        return cart;
    }


    public List<Cart> getCartsInDate(String sDate, String eDate) {
        FakeStoreCartServiceDto[] serviceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts?startdate="+sDate+"&enddate="+eDate,
                FakeStoreCartServiceDto[].class
        );
        List<Cart> ans = new ArrayList<>();
        for (FakeStoreCartServiceDto carts : serviceDto) {
            Cart cart = new Cart();
            cart.setId(carts.getId());
            cart.setUserId(carts.getUserId());
            cart.setDate(carts.getDate());
            List<Products> products = new ArrayList<>();
            for (HashMap<String, Integer> ele : carts.getProducts()) {
                Products product = new Products();
                product.setQuantity(ele.get("quantity"));
                product.setProductId(ele.get("productId"));
                products.add(product);
            }
            cart.setProducts(products);
            ans.add(cart);
        }
        return ans;
    }

    public List<Cart> getUserCarts(Long id) {
        FakeStoreCartServiceDto[] serviceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/user/" + id,
                FakeStoreCartServiceDto[].class
        );
        List<Cart> ans = new ArrayList<>();
        for (FakeStoreCartServiceDto carts : serviceDto) {
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

    public Cart uCart(Cart cart) {
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
        return cart;
    }

    public List<Cart> getAllCarts() {
        FakeStoreCartServiceDto[] serviceDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/",
                FakeStoreCartServiceDto[].class
        );
        List<Cart> ans = new ArrayList<>();
        for (FakeStoreCartServiceDto carts : serviceDto) {
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

    public void dCart(Long id) {
        restTemplate.delete("https://fakestoreapi.com/carts/" + id);
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
        return cart;
    }

}
