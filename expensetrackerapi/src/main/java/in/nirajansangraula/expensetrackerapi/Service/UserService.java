package in.nirajansangraula.expensetrackerapi.Service;

import in.nirajansangraula.expensetrackerapi.entity.UserModel;
import in.nirajansangraula.expensetrackerapi.entity.User;

public interface UserService {
    
    User createUser(UserModel user);

    User readUser(Long id);

    User updateUser(UserModel user, Long id);

    void deleteUser(Long id);

    User getLoggedInUser();
}
