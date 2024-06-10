package demo

import spock.lang.Specification

class E9_Old extends Specification {

  void 'should increase the size of the list when adding a number'() {
    given:
    List<Integer> numbers = [1, 1, 2, 3, 5, 8, 13]
//    List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 5, 8, 13))

    when:
    numbers << 21
//    numbers.add(21)

    then:
    numbers.size() == old(numbers.size()) + 1
  }
}
