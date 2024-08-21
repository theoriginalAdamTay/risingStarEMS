package risingStarEMS;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
  void save(User user);
  Optional<User> findById(String id);
  Optional<User> findByUsername(String username);
  List<User> findAll();
  void deleteById(String id);
  void update(User user);
}
