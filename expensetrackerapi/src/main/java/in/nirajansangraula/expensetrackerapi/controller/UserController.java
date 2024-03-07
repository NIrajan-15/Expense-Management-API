package in.nirajansangraula.expensetrackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import in.nirajansangraula.expensetrackerapi.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import in.nirajansangraula.expensetrackerapi.entity.User;
import in.nirajansangraula.expensetrackerapi.entity.UserModel;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import in.nirajansangraula.expensetrackerapi.exceptions.ResourceNotFoundException;



@RestController
public class UserController {
    
    @Autowired
    private UserService userService;


    // Get user by id
    @GetMapping("/profile")
    public ResponseEntity<User> getUserById()
    {
        return new ResponseEntity<User>(userService.readUser(), HttpStatus.OK);
    }

    // update user 
    @PutMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody UserModel user)
    {
        return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
    }

    // delete user
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deactivate")
    public void deleteUser()
    {
        userService.deleteUser();
    }
    



    
    


}
