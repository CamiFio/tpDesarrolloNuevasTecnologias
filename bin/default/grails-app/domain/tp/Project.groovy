package tp

import java.time.LocalDateTime
import java.util.Collections

class Project {

    String nameProject
    LocalDateTime deadline
    String chat
    static hasMany = [users: User, tasks: Task]
    static constraints = {
        deadline nullable: false
        tasks nullable: false
        nameProject nullable: false
    }

    void addAnewTask(newTask){
        this.addToTasks(newTask)
    }
    
    void addAnewUser(newUser){
        this.addToUsers(newUser)
    }

    void assignTaskToUsers(){
        if (this.users.size() == 0){
            throw new RuntimeException("No hay usuarios a quien asignarle tareas")
        }

        if(this.tasks.size() == 0){
             throw new RuntimeException("No hay tareas para asignar")
        }

        int indexTasks = 0
        int numOfTasksforUsers = (this.tasks.size() / this.users.size()).toInteger()
        numOfTasksforUsers = Math.floor(numOfTasksforUsers)

        def tasksList = tasks.collect()
        Collections.shuffle(tasksList)


        this.users.each { user ->
            List<Task> userTasks = []
            for (int i = 0; i < numOfTasksforUsers; i++) { 
                userTasks.add(tasksList[indexTasks])
                indexTasks++
            }
            user.assignTasks(userTasks) 
        }

        int diff = this.tasks.size() - numOfTasksforUsers* this.users.size()

        if( diff != 0 ){
            for (int i = 0; i < diff; i++){
                this.users[i].agregateUniqueTask(tasksList[indexTasks])
                indexTasks++
            }
        }
    }

    void eliminateAuser(nameUser){
        def userToEliminate = this.users.find { user -> user.name == nameUser }
        if (userToEliminate) {
            this.users.remove(userToEliminate)
        }
        assignTaskToUsers()
    }

}
