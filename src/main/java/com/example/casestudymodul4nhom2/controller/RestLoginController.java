package com.example.casestudymodul4nhom2.controller;

import com.example.casestudymodul4nhom2.model.Entity.Cart;
import com.example.casestudymodul4nhom2.model.Entity.Product;
import com.example.casestudymodul4nhom2.model.User.AppRole;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.example.casestudymodul4nhom2.securityJWT.UserRespo;
import com.example.casestudymodul4nhom2.service.AppRoleService;
import com.example.casestudymodul4nhom2.service.AppUserService;
import com.example.casestudymodul4nhom2.service.IProductService;
import com.example.casestudymodul4nhom2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class RestLoginController {

    @Autowired
    HttpSession httpSession;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AppUserService userService;
    @Autowired
    private AppRoleService roleService;
    @Autowired
    IProductService productService;
  // consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE

    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRespo> login(HttpServletRequest request, @RequestBody AppUser user) {
        AppUser appUser = userService.loadUserByUsername(user.getUsername());

        Long id = appUser.getId();
        String role = appUser.getRoll().getName();
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if(userService.checkLogin(user)) {
                result = jwtService.generateTokenLogin(user.getUsername());
                httpStatus = HttpStatus.OK;
            } else {
                result = "Wrong userId and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            result = "Server Error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        UserRespo userRespo = new UserRespo(result,id,user.getUsername(),user.getAvatar(),role);
        return new ResponseEntity<UserRespo>(userRespo, httpStatus);

//      //  AppUser user1 = userService.loadUserByUsername(user.getUsername());
//        AppUser appUser = userService.loadUserByUsername(user.getUsername());
//        Long id = appUser.getId();
//        String result = "";
//        HttpStatus httpStatus = null;
//        try {
//            if (userService.checkLogin(user)) {
//                result = jwtService.generateTokenLogin(user.getUsername());
//                httpStatus = HttpStatus.OK;
//            } else {
//                result = "Wrong userId and password";
//                httpStatus = HttpStatus.BAD_REQUEST;
//            }
//        } catch (Exception ex) {
//            result = "Server Error";
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        UserRespo userRespo = new UserRespo(result,id,user.getUsername(),user.getAvatar());
//        return new ResponseEntity<UserRespo>(userRespo, httpStatus);
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> Register(@RequestBody AppUser appUser){
        AppRole role = roleService.findById(3L).get();
        System.out.println(role.getName());
        appUser.setRoll(role);
        userService.add(appUser);
        return new ResponseEntity<>("yes",HttpStatus.OK);
    }


    @GetMapping("/showProduct")
    public ResponseEntity<Iterable<Product>> listProduct(){
        Iterable<Product> listProduct= productService.findAll();
        return new ResponseEntity<>(listProduct,HttpStatus.OK);
    }

    @GetMapping("/findAllByName")
    public ResponseEntity<Iterable<Product>> listByNameProduct(@RequestParam("name") String name){
        Iterable<Product> listByNameProduct= productService.findAllByNameContaining(name);
        return new ResponseEntity<>(listByNameProduct,HttpStatus.OK);
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
        Cart cart1 = (Cart) httpSession.getAttribute("Than");
        return new ResponseEntity<>(cart1, HttpStatus.OK);
    }
    @GetMapping("/showCart")
    public ResponseEntity<Cart> showCart(){
        Cart cart1 = (Cart) httpSession.getAttribute("Than");
        return new ResponseEntity<>(cart1, HttpStatus.OK);

    }




}
