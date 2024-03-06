package in.nirajansangraula.expensetrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import in.nirajansangraula.expensetrackerapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    User findByEmail(String email);
}
