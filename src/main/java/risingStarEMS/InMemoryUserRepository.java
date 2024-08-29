package risingStarEMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An implementation of an in-memory user repository making use of a hash map in the absence of a database.
 * @author Adam Tay
 * @version 1.0 unstable
 */
public class InMemoryUserRepository implements UserRepository {
  /**
   * A hash map which maps String IDs to User objects.
   */
  private final Map<String, User> userMap = new HashMap<>();
  
  @Override
  public void save(User user) {
    userMap.put(user.getId(), user);
  }
  
  @Override
  public Optional<User> findById(String id) {
    return Optional.ofNullable(userMap.get(id));
  }
  
  @Override
  public Optional<User> findByUsername(String username) {
    return userMap.values().stream()
        .filter(user -> user.getUsername().equals(username))
        .findFirst();
  }
  
  @Override
  public List<User> findAll() {
    return new ArrayList<>(userMap.values());
  }
  
  @Override
  public void deleteById(String id) {
    userMap.remove(id);
  }
  
  @Override
  public void update(User user) {
    userMap.put(user.getId(), user);
  }
}
