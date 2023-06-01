package tp

import java.time.LocalDateTime

class Task {
    String taskToComplete
    static hasMany = [subTasks: Task]
    Boolean isCompleted = false
    static constraints = {
        taskToComplete nullable: false
    }

    boolean isAllSubtasksCompleted() {
        for (Task subtask in subTasks) {
            if (!subtask.isCompleted) {
                return false
            }
        }
        return true
    }
    
    void addNewSubtask(task){
        this.addToSubTasks(task)
    }

    void completeTask(){
        if(!this.subTasks){
           this.isCompleted = true  
        }
        else{
           this.isCompleted = isAllSubtasksCompleted() 
        }
    }

    void eliminateSubTask(nameTaskToComplete){
        def taskToEliminate = this.subTasks.find { task -> task.taskToComplete == nameTaskToComplete }
        if (taskToEliminate) {
            this.subTasks.remove(taskToEliminate)
        }
    }

    void eliminateTask(){
        if (this.subTasks.size() != 0){
            int size = this.subTasks.size()
            for(int i = size-1; i >= 0; i--){
                this.eliminateSubTask(this.subTasks[i].taskToComplete)
            }
        }
    }

}
