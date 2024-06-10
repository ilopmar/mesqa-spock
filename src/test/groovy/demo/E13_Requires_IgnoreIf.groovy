package demo

import static E13_Requires_IgnoreIf.amazingUserGroup

import spock.lang.IgnoreIf
import spock.lang.Requires
import spock.lang.Specification
import spock.util.environment.Jvm
import spock.util.environment.OperatingSystem

class E13_Requires_IgnoreIf extends Specification {

  @Requires({ OperatingSystem.current.linux })
  void 'should only run on Linux'() {
    expect:
    true
  }

  @Requires({ OperatingSystem.current.windows })
  void 'should only run on Windows'() {
    expect:
    false
  }

  @IgnoreIf({ Jvm.current.java17Compatible })
  void 'should be ignored in Java 17+'() {
    expect:
    false
  }

  @IgnoreIf({ Jvm.current.java17 })
  void 'should be ignored only in Java 17'() {
    expect:
    true
  }

  @Requires({ amazingUserGroup('Madrid-GUG') })
  void 'should run for amazing user groups'() {
    expect:
    true
  }

  static boolean amazingUserGroup(String userGroup) {
    userGroup in ['Madrid-GUG', 'Madrid-JUG']
  }

}
