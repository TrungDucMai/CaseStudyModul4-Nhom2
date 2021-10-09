package com.example.casestudymodul4nhom2.controller;

import com.example.casestudymodul4nhom2.model.User.AppRole;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.example.casestudymodul4nhom2.securityJWT.UserRespo;
import com.example.casestudymodul4nhom2.service.AppRoleService;
import com.example.casestudymodul4nhom2.service.AppUserService;
import com.example.casestudymodul4nhom2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "*")
public class RestLoginController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AppUserService userService;
    @Autowired
    private AppRoleService roleService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRespo> login(HttpServletRequest request, @RequestBody AppUser user) {
        AppUser appUser = userService.loadUserByUsername(user.getUsername());

        Long id = appUser.getId();
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
        UserRespo userRespo = new UserRespo(result,id,user.getUsername(),user.getAvatar());
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


}
