package in.nirajansangraula.expensetrackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import in.nirajansangraula.expensetrackerapi.entity.AuthModel;
import in.nirajansangraula.expensetrackerapi.entity.JwtResponse;
import in.nirajansangraula.expensetrackerapi.entity.User;
import in.nirajansangraula.expensetrackerapi.entity.UserModel;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import in.nirajansangraula.expensetrackerapi.util.JwtTokenUtil;
import in.nirajansangraula.expensetrackerapi.Security.CustomUserDetailService;
import in.nirajansangraula.expensetrackerapi.Service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import BadCredentialException
import org.springframework.security.authentication.BadCredentialsException;



@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    // login the user
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws Exception
    {
        
        authenticate(authModel.getEmail(), authModel.getPassword());

        // generate the jwt token
        final UserDetails userDetails = userDetailService.loadUserByUsername(authModel.getEmail());
        final String jwtToken = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<JwtResponse>(new JwtResponse(jwtToken), HttpStatus.OK);
    }

    
    private void authenticate(String email, String password) throws Exception
    {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }catch(DisabledException e)
        {
            throw new Exception("User Disabled!");
        }catch(BadCredentialsException e)
        {
            throw new Exception("Invalid Credentials!");
        }
    }

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserModel user)
    {
        return new ResponseEntity<User> (userService.createUser(user), HttpStatus.CREATED);
    }
    
}
