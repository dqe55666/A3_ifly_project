package cn.org.rsrs.project.project_a3_ifly.repository;

import cn.org.rsrs.project.project_a3_ifly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
