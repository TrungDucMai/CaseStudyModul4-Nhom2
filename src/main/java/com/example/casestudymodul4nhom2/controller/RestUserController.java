package com.example.casestudymodul4nhom2.controller;

import com.example.casestudymodul4nhom2.model.Entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
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
