package demo

import spock.lang.Specification

class E8_Stubbing extends Specification {

  void 'should return predefined value'() {
    given:
//    UserRepository stubbedRepository = Stub() {
//      findById(_) >> new User('John', 'Doe')
//    }
    UserRepository stubbedRepository = Stub(UserRepository)
    stubbedRepository.findById(_) >> new User('John', 'Doe')

    when:
    var user = stubbedRepository.findById(1)

    then:
    user.name == 'John'
    user.lastName == 'Doe'
  }

  void 'should return different values in every call'() {
    given:
    UserRepository stubbedRepository = Stub(UserRepository) {
      findById(_) >>> [
          new User('John', 'Doe'),
          new User('Jane', 'Doe')
      ]
    }

    when:
    var user1 = stubbedRepository.findById(1)
    var user2 = stubbedRepository.findById(1)
    var user3 = stubbedRepository.findById(1)

    then:
    user1.name == 'John'
    user2.name == 'Jane'
    user3.name == 'Jane'
  }

  void 'should return values depending the parameters'() {
    given:
    UserRepository stubbedRepository = Stub(UserRepository) {
      findById(20) >> new User('Jane', 'Doe')
      findById(11) >> new User('John', 'Doe')
    }

    when:
    var user1 = stubbedRepository.findById(11)
    var user2 = stubbedRepository.findById(20)

    then:
    user1.name == 'John'
    user2.name == 'Jane'
  }

  void 'should throw an exception'() {
    given:
    UserRepository stubbedRepository = Stub(UserRepository) {
      findById(_) >> { throw new RuntimeException(msg) }
    }

    when:
    stubbedRepository.findById(1)

    then:
    var e = thrown RuntimeException
    e.message == msg

    where:
    msg = 'User does not exist'
  }

  void 'should stub more than one method'() {
    given:
    UserRepository stubbedRepository = Stub(UserRepository) {
      findById(_) >> new User('Peter', 'Smith')
      findAllByLastName('Doe') >> [
          new User('John', 'Doe'),
          new User('Jane', 'Doe')
      ]
    }

    expect:
    stubbedRepository.findById(99).name == 'Peter'
    stubbedRepository.findAllByLastName('Doe').size() == 2
  }
}
