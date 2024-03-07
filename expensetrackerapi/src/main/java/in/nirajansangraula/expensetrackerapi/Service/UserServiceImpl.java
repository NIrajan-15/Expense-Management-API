package in.nirajansangraula.expensetrackerapi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.nirajansangraula.expensetrackerapi.entity.User;
import in.nirajansangraula.expensetrackerapi.entity.UserModel;
import in.nirajansangraula.expensetrackerapi.exceptions.ItemAlreadyExistsException;
import in.nirajansangraula.expensetrackerapi.exceptions.ResourceNotFoundException;
import in.nirajansangraula.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // create a new user
    @Override
    public User createUser(UserModel user)
    {
        if(userRepo.existsByEmail(user.getEmail()))
        {
            throw new ItemAlreadyExistsException("Email Already Exists!");
        }
        if(userRepo.existsByUsername(user.getUsername()))
        {
            throw new ItemAlreadyExistsException("Username Already Exists!");
        }
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(newUser);
    }

    // read a user
    @Override
    public User readUser(){
        Long userId = getLoggedInUser().getId();
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for the id: "+ userId));
    }

    // update a user
    @Override
    public User updateUser(UserModel user)
    {
        User existingUser = readUser();
        existingUser.setUsername(user.getUsername() != null ? user.getUsername() : existingUser.getUsername());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? passwordEncoder.encode(user.getPassword()) : existingUser.getPassword());
        return userRepo.save(existingUser);
        
    }

    // delete a user
    @Override
    public void deleteUser()
    {
        User user = readUser();
        userRepo.delete(user);
    }

    // get the currently logged in user
    @Override
    public User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User loggedUser = userRepo.findByEmail(email);
        
        if(loggedUser == null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        return loggedUser;
    }
}
