package demo

import spock.lang.Specification

class E6_Lifecycle extends Specification {

  void setupSpec() {
    println '> setupSpec'
  }

  void setup() {
    println '>> setup'
  }

  void cleanup() {
    println '>> cleanup'
  }

  void cleanupSpec() {
    println '> cleanSpec'
  }

  void 'test'() {
    expect:
    println '>>> test'
  }

  void 'test 2'() {
    expect:
    println '>>> test 2'
  }

}
