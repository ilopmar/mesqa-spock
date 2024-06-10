package demo

import spock.lang.Specification

class E8B_Stubbing extends Specification {

  void 'search some users'() {
    given: 'a stubbed repository service'
    var userRepository = Stub(UserRepository) {
      findAllByLastName(lastName) >> [
          new User('John', 'Doe'),
          new User('Jane', 'Doe'),
          new User('Peter', 'Smith'),
      ]
    }

    and: 'a search service'
    var searchService = new SearchService(userRepository)

    when: 'searching for some users'
    var results = searchService.getUsersNameByLastName(lastName)

    then:
    results.size() == 2
    results == ['JOHN', 'JANE']

    where:
    lastName = 'Doe'
  }
}
