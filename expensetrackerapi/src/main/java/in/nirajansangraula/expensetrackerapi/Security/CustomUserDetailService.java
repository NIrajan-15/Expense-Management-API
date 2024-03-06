package in.nirajansangraula.expensetrackerapi.Security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import in.nirajansangraula.expensetrackerapi.repository.UserRepository;
import in.nirajansangraula.expensetrackerapi.entity.User;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class CustomUserDetailService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         
        User existingUser = userRepository.findByEmail(email);
        if(existingUser == null){
            throw new UsernameNotFoundException("User not found");
        }
        
        return new org.springframework.security.core.userdetails.User(existingUser.getUsername(), existingUser.getPassword(), new ArrayList<>());
    }
}
