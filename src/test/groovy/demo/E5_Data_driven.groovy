package demo

import spock.lang.Specification

class E5_Data_driven extends Specification {

  void 'all numbers should be even'() {
    expect:
    number % 2 == 0

    where:
    number << [-10, 0, 2, 42, 1000]
  }

  void 'should get the minimum'() {
    expect:
    Math.min(x, y) == result

    where:
    x << [1, -1, 0]
    y << [2, -2, 1]
    result << [1, -2, 0]
  }

  void 'should get the minimum of two numbers'() {
    expect:
    Math.min(x, y) == result

    where:
    x  | y  | result
    1  | 2  | 1
    -1 | -2 | -2
    0  | 1  | 0
  }

  void 'should check that minimum of #x and #y is #result'() {
    expect:
    Math.min(x, y) == result

    where:
    x  | y  | result
    1  | 2  | 1
    -1 | -2 | -2
    0  | 1  | 0
  }

  void 'should read data from an iterable'() {
    given:
    User user = new User(theName as String, theLastName as String)
//    User user = [name: theName, lastName: theLastName] as User

    expect:
    user instanceof User
    user.name == theName
    user.lastName == theLastName

    where:
    [theName, theLastName] << new File('src/test/resources/users.csv')
        .readLines()
        .collect { it.split(',') }
  }
}
