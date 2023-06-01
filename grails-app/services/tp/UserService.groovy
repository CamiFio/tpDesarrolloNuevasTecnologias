package tp

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def createUser(String name, String password) {
        User user = new User(name, password)
        p.save()

    }
}
