package demo;

public class UserService {

  private final NotificationService notificationService;

  public UserService(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  void createUser(String name, String lastName) {
    var user = new User(name, lastName);

    // Business logic, store user on DB,...

    notificationService.sendNotification(user, "User created");
  }
}
