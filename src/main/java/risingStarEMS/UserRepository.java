package risingStarEMS;

import java.util.Optional;

public interface UserRepository {
  boolean existsByUsername(String username);

  Optional<User> findByUsername(String username);

  void save(User user);

  boolean authenticate(String username, String password);

  void deleteByUsername(String username);

  void update(User user);
}
