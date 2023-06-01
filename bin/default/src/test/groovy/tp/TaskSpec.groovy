package tp

import java.time.LocalDateTime

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification



class TaskSpec extends Specification implements DomainUnitTest<Task> {

    def setup() {
    }

    def cleanup() {
    }

    void "can create a new task"() {
        given: "can create"
        def firstTask = new Task()

        when: "nothing"

        then: "task created"
        firstTask != null
    
    }

    void "can create a new task with specific properties"(){
        given: "create a new task"
        def secondTask = new Task(taskToComplete: "Excercise 1")

        when: "nothing"

        then: "task create succes"
        secondTask != null
        secondTask.taskToComplete == "Excercise 1"
    }

    void "can create a new task and complete"(){
        given: "new task"
        def thirdTask = new Task(taskToComplete: "Excercise 2")

        when: "task completed"
        thirdTask.completeTask()

        then: "task completed succefully"
        thirdTask != null
        thirdTask.taskToComplete == "Excercise 2"
        thirdTask.isCompleted == true
    }

    void "can create a task with subtask"(){
        given: "new task and subtasks"
        def fourTask = new Task(taskToComplete: "Excercise 3")
        def subtask1 = new Task(taskToComplete: "sub1")
        def subtask2 = new Task(taskToComplete: "sub2")

        when: "assign subtasks to task and not to complete the first task"
        fourTask.addNewSubtask(subtask1)
        fourTask.addNewSubtask(subtask2)
        fourTask.completeTask()

        then: "task not complete"
        fourTask.isCompleted == false 
    }

    void "Can create a task with subtasks and complete"(){
        given: "new task and subtasks"
        def fourTask = new Task(taskToComplete: "Excercise 3")
        def subtask1 = new Task(taskToComplete: "sub1")
        def subtask2 = new Task(taskToComplete: "sub2")

        when: "assign subtasks to task and try to complete the first task"
        fourTask.addNewSubtask(subtask1)
        fourTask.addNewSubtask(subtask2)
        fourTask.subTasks[0].completeTask()
        fourTask.subTasks[1].completeTask()
        fourTask.completeTask()

        then: "task complete"
        fourTask.isCompleted == true
    }

    void "can create a task with subtasks and eliminate yours subtasks"(){
        given: "new task and subtasks"
        def fiveTask = new Task(taskToComplete: "Excercise 3")
        def subtask1 = new Task(taskToComplete: "sub1")
        def subtask2 = new Task(taskToComplete: "sub2")
        fiveTask.addNewSubtask(subtask1)
        fiveTask.addNewSubtask(subtask2)

        when: "eliminate subtasks"
        fiveTask.eliminateTask()

        then: "subtasks eliminate"
        fiveTask.subTasks.size() == 0
    }


}
