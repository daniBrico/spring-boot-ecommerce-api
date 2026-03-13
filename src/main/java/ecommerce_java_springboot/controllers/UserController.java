package ecommerce_java_springboot.controllers;

import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<UserModel> getUsers() {
        return userService.getUsers();
    }

    @PostMapping()
    public UserModel setUser(@RequestBody UserModel user) {
        return this.userService.setUser(user);
    }
}
