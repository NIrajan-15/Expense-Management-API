package in.nirajansangraula.expensetrackerapi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nirajansangraula.expensetrackerapi.entity.User;
import in.nirajansangraula.expensetrackerapi.entity.UserModel;
import in.nirajansangraula.expensetrackerapi.exceptions.ItemAlreadyExistsException;
import in.nirajansangraula.expensetrackerapi.exceptions.ResourceNotFoundException;
import in.nirajansangraula.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepo;

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
        return userRepo.save(newUser);
    }

    @Override
    public User readUser(Long id){
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for the id: "+ id));
    }

    @Override
    public User updateUser(UserModel user, Long id)
    {
        User existingUser = readUser(id);
        existingUser.setUsername(user.getUsername() != null ? user.getUsername() : existingUser.getUsername());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
        return userRepo.save(existingUser);
        
    }

    @Override
    public void deleteUser(@RequestParam Long id)
    {
        User user = readUser(id);
        userRepo.delete(user);
    }
}
