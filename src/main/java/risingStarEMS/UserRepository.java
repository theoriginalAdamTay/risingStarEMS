package risingStarEMS;

import java.util.List;
import java.util.Optional;

/**
 * An interface providing functionality associated with the user repository.
 * @author Adam Tay
 * @version 1.0 unstable
 *
 */
public interface UserRepository {
  /**
   * A function for saving a user to the repository.
   * @param user An instance of a User object to save to the repository.
   */
  void save(User user);
  /**
   * A function for retrieving a user from the repository, given their ID.
   * @param id The ID of the user to retrieve from the repository.
   * @return The user associated with the given ID.
   */
  Optional<User> findById(String id);
  /**
   * A function for retrieving a user from the repository, given their username.
   * @param username The username of the user to retrieve from the repository.
   * @return The user associated with the given username.
   */
  Optional<User> findByUsername(String username);
  /**
   * A function for retrieving all users saved in the repository.
   * @return A list of all users saved in the repository.
   */
  List<User> findAll();
  /**
   * A function for deleting a user from the repository.
   * @param id The ID of the user to delete from the repository.
   */
  void deleteById(String id);
  /**
   * A function for updating a user's details.
   * @param user The user whose details are to be updated.
   */
  void update(User user);
}
