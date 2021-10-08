package com.example.casestudymodul4nhom2.controller;

import com.example.casestudymodul4nhom2.model.Entity.Product;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.example.casestudymodul4nhom2.service.AppUserService;
import com.example.casestudymodul4nhom2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
@CrossOrigin(origins = "*")
public class RestSellerController {
    @Autowired
    private IProductService productService;
    @Autowired
    private AppUserService userService;


    @GetMapping("/productList/{id}")
    public ResponseEntity<List<Product>> getProductByUsername(@PathVariable Long id) {
        AppUser user = userService.findById(id);

        List<Product> productList = (List<Product>) productService.findProductByUser(user);

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("/product/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
