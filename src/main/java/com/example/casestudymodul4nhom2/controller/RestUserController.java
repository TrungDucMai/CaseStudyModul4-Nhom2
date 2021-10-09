package com.example.casestudymodul4nhom2.controller;

import com.example.casestudymodul4nhom2.model.Entity.Cart;
import com.example.casestudymodul4nhom2.model.Entity.Product;
import com.example.casestudymodul4nhom2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class RestUserController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    IProductService productService;

    @GetMapping("/showProduct")
    public ResponseEntity<Iterable<Product>> listProduct(){
        Iterable<Product> listProduct= productService.findAll();
        return new ResponseEntity<>(listProduct,HttpStatus.OK);
    }

    @GetMapping("/findAllByName/")
    public ResponseEntity<Iterable<Product>> listByNameProduct(@RequestParam("name") String name){
        Iterable<Product> listByNameProduct= productService.findAllByNameContaining(name);
        return new ResponseEntity<>(listByNameProduct,HttpStatus.OK);
    }

    @GetMapping("/findAllByPrice/")
    public ResponseEntity<Iterable<Product>> listByPriceProduct(@Param("lowestPrice") double lowestPrice, @Param("highestPrice") double highestPrice){
        Iterable<Product> listProductByPrice= productService.findAllByPriceBetween(lowestPrice,highestPrice);
        return new ResponseEntity<>(listProductByPrice,HttpStatus.OK);
    }



    @GetMapping("/addtoCart/{id}")
    public ResponseEntity<Cart> addtoCart(@PathVariable Long id) {
        int quantity = 1;
        Product product = productService.findById(id).get();
        if (httpSession.getAttribute("Than") == null) {
            Cart cart = new Cart();
            List<Product> productList = new ArrayList<>();
            product.setQuantity(quantity);
            productList.add(product);
            cart.setProductList(productList);
            httpSession.setAttribute("Than", cart);

        } else {
            Cart cart = (Cart) httpSession.getAttribute("Than");
            List<Product> productList = cart.getProductList();
            boolean check = false;
            for (Product product1 : productList) {
                if (product1.getId() == product.getId()) {
                    product1.setQuantity(product1.getQuantity() + quantity);
                    check = true;
                }
            }
            if (check = false) {
                product.setQuantity(quantity);
                productList.add(product);
            }
            httpSession.setAttribute("Than", cart);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("/showCart")
    public ResponseEntity<Cart> showCart(){
        Cart cart = (Cart) httpSession.getAttribute("Than");
        return new ResponseEntity<>(cart, HttpStatus.OK);

    }










}





