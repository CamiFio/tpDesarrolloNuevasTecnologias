package tp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }
    void "can create a user"(){
        given: "user"
        def user = new User()
        when: "nothing"
        then: "user create"
        user != null
    }

    void "can create a user with specific name and password"() {
        given: "can create"
        def user = new User()
        
        when: "name and pass"
        user.name = "camila"
        user.password = "abc123"

        then: "user with name and pass"
        user.name == "camila"
        user.password == "abc123"
    }


}
