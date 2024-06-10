package demo

import spock.lang.Specification

class E3_PowerAsserts extends Specification {

  void 'should fail with numbers'() {
    expect:
    2 * 3 == 5 * 4
  }

  void 'should fail with maps and lists'() {
    given:
    Map<String, Object> data = Map.of(
        "name", "Iván",
        "age", 44,
        "kids", List.of(
          Map.of(
            "name", "Judith",
            "age", 16
          ),
          Map.of(
            "name", "Adriana",
            "age", 13
          )
      )
    )

//    Map<String, Object> data2 = [
//        name: 'Iván',
//        age : 44,
//        kids: [
//            [name: 'Judith', age: 16], [name: 'Adriana', age: 13]
//        ]
//    ]

    expect:
    data.kids.name.first() == 'Adriana'

//    data2.kids.name.first() == 'Adriana'
//    ((List<Map<String, Object>>)data.get("kids")).getFirst().get("name") == 'Adriana'
  }
}
