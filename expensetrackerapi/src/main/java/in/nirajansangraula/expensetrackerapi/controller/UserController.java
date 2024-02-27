package in.nirajansangraula.expensetrackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import in.nirajansangraula.expensetrackerapi.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import in.nirajansangraula.expensetrackerapi.entity.User;
import in.nirajansangraula.expensetrackerapi.entity.UserModel;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;


@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserModel user)
    {
        return new ResponseEntity<User> (userService.createUser(user), HttpStatus.CREATED);
    }
    


}
