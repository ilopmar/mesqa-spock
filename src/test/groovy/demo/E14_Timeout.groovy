package demo

import java.util.concurrent.TimeUnit
import spock.lang.Specification
import spock.lang.Timeout

class E14_Timeout extends Specification {

  @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
  void 'should fail after 500 milliseconds'() {
    given:
    Thread.sleep 200
    String name = 'Iván'

    println "Hello $name"

    expect:
    true
  }

}
