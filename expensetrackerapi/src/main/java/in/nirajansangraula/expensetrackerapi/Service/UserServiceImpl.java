package in.nirajansangraula.expensetrackerapi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nirajansangraula.expensetrackerapi.entity.User;
import in.nirajansangraula.expensetrackerapi.entity.UserModel;
import in.nirajansangraula.expensetrackerapi.exceptions.ItemAlreadyExistsException;
import in.nirajansangraula.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;

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
}
