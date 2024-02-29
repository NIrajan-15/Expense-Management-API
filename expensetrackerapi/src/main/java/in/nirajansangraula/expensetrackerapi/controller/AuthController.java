package in.nirajansangraula.expensetrackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import in.nirajansangraula.expensetrackerapi.entity.User;
import in.nirajansangraula.expensetrackerapi.entity.UserModel;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import in.nirajansangraula.expensetrackerapi.Service.UserService;


@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    
    // login the user
    @PostMapping("/login")
    public ResponseEntity<String> login()
    {
        return new ResponseEntity<String>("user is logged in", HttpStatus.OK);
    }

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserModel user)
    {
        return new ResponseEntity<User> (userService.createUser(user), HttpStatus.CREATED);
    }
    
}
