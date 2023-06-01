package tp

class User {
    String name
    String password
    static hasMany = [taskToDo: Task]
    static constraints = {
        name nullable: false, unique: true
        password nullable: false
    }

    void assignTasks(tasks){
        this.taskToDo = tasks
    }

    void agregateUniqueTask(task){
        this.taskToDo.add(task)
    }
}
