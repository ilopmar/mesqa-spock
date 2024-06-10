package demo;

import java.util.List;

public class SearchService {

  private final UserRepository userRepository;

  public SearchService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  List<String> getUsersNameByLastName(String lastName) {
    List<User> users = userRepository.findAllByLastName(lastName);

    return users
        .stream()
        .filter(u -> u.getLastName().equals(lastName))
        .map(u -> u.getName().toUpperCase())
        .toList();
  }
}
