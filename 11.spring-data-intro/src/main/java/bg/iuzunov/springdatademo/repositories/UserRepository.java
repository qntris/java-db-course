package bg.iuzunov.springdatademo.repositories;

import bg.iuzunov.springdatademo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

}
