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
//Kết nối vs fontend
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

  //  @Autowired



//    @GetMapping("/addtoCart/{id}")
//    public ResponseEntity<Cart> addtoCart(@PathVariable Long id) {
//        int quantity =1;
//
//
//
//
//
//
//    }
//



}
