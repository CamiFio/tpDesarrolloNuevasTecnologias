package tp

import java.time.LocalDateTime

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ProjectSpec extends Specification implements DomainUnitTest<Project> {

    def setup() {
    }

    def cleanup() {
    }

    void "create a project empty"(){
        given: "project"
        Project project = new Project()

        when: "nothing"

        then: "project is empty"
        project != null 
    }
    
    void "can create a project and add tasks"(){
        given: "project and tasks"
        Task task1 = new Task(taskToComplete: "Excercise 1")
        Task task2 = new Task(taskToComplete: "Excercise 2")
        Task task3 = new Task(taskToComplete: "Excercise 3")
        Task task4 = new Task(taskToComplete: "Excercise 4")
        LocalDateTime deadline = LocalDateTime.of(2024, 02, 28, 10, 0, 0)
        Project firstProject = new Project(deadline: deadline, nameProject: "Project 1")

        when: "Add a news tasks"
        firstProject.addAnewTask(task1)
        firstProject.addAnewTask(task2)
        firstProject.addAnewTask(task3)
        firstProject.addAnewTask(task4)

        then: "Create a succefully project"
        firstProject.deadline == deadline
        firstProject.tasks.size() == 4
        firstProject.tasks[0] == task1
        firstProject.tasks[1] == task2
        firstProject.tasks[2] == task3
        firstProject.tasks[3] == task4

    }

    void "a project can have many users "(){
        given: "project and users"
        Project project = new Project(name: "ProjectExample")
        User user1 = new User(name: "Abc", password: "123456")
        User user2 = new User(name: "Def", password: "789012")
        User user3 = new User(name: "Ghi", password: "345678")

        when: "Users can be part of a project"
        project.addAnewUser(user1)
        project.addAnewUser(user2)
        project.addAnewUser(user3)

        then: "project have 3 users"
        project.users.size() == 3
    }

    void "a project can assign task to the users"(){
        given: "project, users and tasks"
        Project project = new Project(name: "ProjectExample")
        User user1 = new User(name: "Abc", password: "123456")
        User user2 = new User(name: "Def", password: "789012")
        Task task1 = new Task(taskToComplete: "Excercise 1")
        Task task2 = new Task(taskToComplete: "Excercise 2")
        Task task3 = new Task(taskToComplete: "Excercise 3")
        Task task4 = new Task(taskToComplete: "Excercise 4")
        project.addAnewUser(user1)
        project.addAnewUser(user2)
        project.addAnewTask(task1)
        project.addAnewTask(task2)
        project.addAnewTask(task3)
        project.addAnewTask(task4)

        when: "project can assign tasks to users"
        project.assignTaskToUsers()

        then: "each users have 2 tasks"
        project.users[0].taskToDo.size() == 2
        project.users[1].taskToDo.size() == 2
    }

    void "project with odd tasks"(){
        given: "project, users and tasks"
        Project project = new Project(name: "ProjectExample")
        User user1 = new User(name: "Abc", password: "123456")
        User user2 = new User(name: "Def", password: "789012")
        Task task1 = new Task(taskToComplete: "Excercise 1")
        Task task2 = new Task(taskToComplete: "Excercise 2")
        Task task3 = new Task(taskToComplete: "Excercise 3")
        Task task4 = new Task(taskToComplete: "Excercise 4")
        Task task5 = new Task(taskToComplete: "Excercise 5")
        project.addAnewUser(user1)
        project.addAnewUser(user2)
        project.addAnewTask(task1)
        project.addAnewTask(task2)
        project.addAnewTask(task3)
        project.addAnewTask(task4)
        project.addAnewTask(task5)
        when: "project can assign tasks to users"
        project.assignTaskToUsers()

        then: "each users have 2 tasks"
        project.users[0].taskToDo.size() == 3
        project.users[1].taskToDo.size() == 2
        
    }

    void "can eliminate a user and update tasks"(){
        given: "project, users and tasks"
        Project project = new Project(name: "ProjectExample")
        User user1 = new User(name: "Abc", password: "123456")
        User user2 = new User(name: "Def", password: "789012")
        Task task1 = new Task(taskToComplete: "Excercise 1")
        Task task2 = new Task(taskToComplete: "Excercise 2")
        Task task3 = new Task(taskToComplete: "Excercise 3")
        Task task4 = new Task(taskToComplete: "Excercise 4")
        Task task5 = new Task(taskToComplete: "Excercise 5")
        project.addAnewUser(user1)
        project.addAnewUser(user2)
        project.addAnewTask(task1)
        project.addAnewTask(task2)
        project.addAnewTask(task3)
        project.addAnewTask(task4)
        project.addAnewTask(task5)
        project.assignTaskToUsers()

        when: "project eliminate a user"
        project.eliminateAuser("Abc")

        then: "user have 5 tasks"
        project.users[0].taskToDo.size() == 5
        
    }


}
