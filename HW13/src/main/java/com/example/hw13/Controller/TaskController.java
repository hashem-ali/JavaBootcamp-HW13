package com.example.hw13.Controller;

import com.example.hw13.Pojo.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<Task>();
    @GetMapping("/get")
    public ArrayList<Task> displayAll(){
        return tasks;
    }

    @PostMapping("/add")
    public String addTask(@RequestBody Task task){
        tasks.add(task);
        return "task added successfully";
    }

    @PutMapping("update/{index}")
    public String updateTask(@PathVariable int index, @RequestBody Task task){
        tasks.set(index, task);
        return "Updated successfully";
    }

    @DeleteMapping("delete/{id}")
    public String deleteTask(@PathVariable int id){
        tasks.remove(id);
        return "Deleted successfully";
    }

    @GetMapping("/find/{title}")
    public Task findTask(@PathVariable String title){
        for (Task task: tasks) {
            if(task.getTitle().equals(title)){
                return task;
            }
        }
        return null;
    }
    @PutMapping("/change/{id}")
    public String changeStatus(@PathVariable String id, @RequestBody String status){
        for (Task task: tasks) {
            if(task.getId().equals(id)){
                task.setStatus(status);
                return "Changed successfully";
            }
        }
        return "Not Found";
    }
}
