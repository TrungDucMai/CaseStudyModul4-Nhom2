package com.example.casestudymodul4nhom2.controller;

import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.example.casestudymodul4nhom2.securityJWT.UserRespo;
import com.example.casestudymodul4nhom2.service.AppUserService;
import com.example.casestudymodul4nhom2.service.IAppUserService;
import com.example.casestudymodul4nhom2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
@Configuration
@RequestMapping("/rest")
public class RestLoginController {

    @Autowired
    AppUserService userService;
    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserRespo> login(HttpServletRequest request, @RequestBody AppUser user) {
       AppUser user1 = userService.loadUserByUsername(user.getUsername());
       Long id = user1.getId();
        String result = "";
        HttpStatus httpStatus = null;
        try {
            if (userService.checkLogin(user)) {
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
        UserRespo userRespo = new UserRespo(id, result,user.getUsername(),user.getAvatar());
        return new ResponseEntity<UserRespo>(userRespo, httpStatus);
    }
}
