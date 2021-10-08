package com.example.casestudymodul4nhom2.controller;

import com.example.casestudymodul4nhom2.model.User.AppRole;
import com.example.casestudymodul4nhom2.model.User.AppUser;
import com.example.casestudymodul4nhom2.securityJWT.UserRespo;
import com.example.casestudymodul4nhom2.service.AppRoleService;
import com.example.casestudymodul4nhom2.service.AppUserService;
import com.example.casestudymodul4nhom2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class RestAdminController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AppUserService userService;
    @Autowired
    private AppRoleService roleService;

    /* ---------------- GET ALL USER ------------------------ */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<AppUser>> getAllUser() {
        return new ResponseEntity<List<AppUser>>(userService.findAll(), HttpStatus.OK);
    }
    /* ---------------- GET USER BY ID ------------------------ */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        AppUser user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        }
        return new ResponseEntity<Object>("Not Found User", HttpStatus.NO_CONTENT);
    }
    /* ---------------- CREATE NEW USER ------------------------ */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody AppUser user) {
        AppRole role = roleService.findById(3L).get();
        System.out.println(role.getName());
        user.setRoll(role);
        if (userService.add(user)) {
            return new ResponseEntity<String>("Created!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("User Existed!", HttpStatus.BAD_REQUEST);
        }
    }
    /* ---------------- DELETE USER ------------------------ */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<String>("Deleted!", HttpStatus.OK);
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<AppUser>> getUserByRole(@PathVariable Long id) {
        AppRole role = roleService.findById(id).get();
        List<AppUser> appUserList = (List<AppUser>) userService.findUserByRole(role);
        return new ResponseEntity<List<AppUser>>(appUserList, HttpStatus.OK);
    }
    @RequestMapping(value = "/role/remove/{id}", method = RequestMethod.GET)
    public ResponseEntity<AppUser> lockUser(@PathVariable Long id) {
        AppUser user = userService.findById(id);
        AppRole role = roleService.findById(4L).get();
        user.setRoll(role);
        user.setStatus("da khoa");
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
