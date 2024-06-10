package demo

import java.nio.file.Files
import spock.lang.Specification
import spock.lang.TempDir
import spock.util.io.FileSystemFixture

class E15_FileSystemFixture extends Specification {

  @TempDir
  FileSystemFixture fsFixture

  void 'FileSystemFixture can create a directory structure'() {
    when:
    fsFixture.create {
      dir('src') {
        dir('main') {
          dir('groovy') {
            file('HelloWorld.groovy') << 'println "Hello World"'
          }
        }
        dir('test/resources') {
          file('META-INF/MANIFEST.MF') << 'some content'
          copyFromClasspath('/users.csv')
        }
      }
    }

    then:
    Files.isDirectory(fsFixture.resolve('src/main/groovy'))
    Files.isDirectory(fsFixture.resolve('src/test/resources/META-INF'))
    fsFixture.resolve('src/main/groovy/HelloWorld.groovy').text == 'println "Hello World"'
    fsFixture.resolve('src/test/resources/META-INF/MANIFEST.MF').text == 'some content'
    fsFixture.resolve('src/test/resources/users.csv').text.contains('Sheldon')
  }

}
