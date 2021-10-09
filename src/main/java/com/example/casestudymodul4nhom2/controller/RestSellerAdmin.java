package com.example.casestudymodul4nhom2.controller;

import com.example.casestudymodul4nhom2.model.Entity.Compound;
import com.example.casestudymodul4nhom2.model.Entity.Product;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.example.casestudymodul4nhom2.service.AppUserService;
import com.example.casestudymodul4nhom2.service.CommentService;
import com.example.casestudymodul4nhom2.service.CompoundService;
import com.example.casestudymodul4nhom2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
@CrossOrigin(origins = "*")
public class RestSellerAdmin {
    @Autowired
    private IProductService productService;
    @Autowired
    private AppUserService userService;
    @Autowired
    private CompoundService compoundService;


    @GetMapping("/productList/{id}")
    public ResponseEntity<List<Product>> getProductByUsername(@PathVariable Long id) {
        AppUser user = userService.findById(id);

        List<Product> productList = (List<Product>) productService.findProductByUser(user);

        return new ResponseEntity(productList, HttpStatus.OK);
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

    @PostMapping("/listPage")
    public ResponseEntity<Page<Product>> listPageProduct(@RequestParam String page){
        Pageable pageable = PageRequest.of(Integer.parseInt(page),3);
        Page<Product> postPage = productService.findAll(pageable);
        return new ResponseEntity<>(postPage,HttpStatus.OK);
    }
    @PostMapping("/createCompound")
    public ResponseEntity<Compound> createCompound(Compound compound){
        compoundService.save(compound);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
