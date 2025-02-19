package demo

import spock.lang.Specification

class E11_VerifyAll extends Specification {

  void 'should check all the asserts even if one of them fails'() {
    given:
    User user = new User(name: 'Iván', lastName: 'López')

    expect:
    verifyAll {
      user.name != 'Iván'
      user.lastName != 'López'
    }
  }
}
