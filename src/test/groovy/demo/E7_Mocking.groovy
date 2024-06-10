package demo

import spock.lang.Specification
import spock.lang.Subject

class E7_Mocking extends Specification {

  private final NotificationService notificationService = Mock()

  @Subject
  private UserService userService

  void setup() {
    userService = new UserService(notificationService)
  }

  void 'should send a notification when the someone registers'() {
    when:
    userService.createUser('Iván', 'López')

    then:
    1 * notificationService.sendNotification(_, 'User created')
  }

  void 'should check constraints on interactions'() {
    when:
    userService.createUser('Iván', 'López')

    then:
    1 * notificationService.sendNotification({ User u -> u.name == 'Iván' && u.lastName == 'López' }, 'User created')
  }

  void 'should mock an implementation'() {
    given:
    var user = new User('Iván', 'López')
    var mockedEmailService = Mock(EmailService)
    //    EmailService mockedEmailService = Mock()

    when:
    mockedEmailService.sendEmail(user, 'How are you?')

    then:
    1 * mockedEmailService.sendEmail(user, 'How are you?')
  }

  void 'should check the order'() {
    given:
    var user = new User('Iván', 'López')

    when:
    notificationService.sendNotification(user, 'msg1')
    notificationService.sendNotification(user, 'msg2')
    notificationService.sendNotification(user, 'msg3')

    then:
    1 * notificationService.sendNotification(user, 'msg1')

    then:
    1 * notificationService.sendNotification(user, 'msg2')

    then:
    1 * notificationService.sendNotification(user, 'msg3')
  }

  void 'should mock static methods'() {
    given:
    DataHelper.metaClass.static.currentTime = { -> 42 }

    expect:
    DataHelper.currentTime() == 42
  }
}
