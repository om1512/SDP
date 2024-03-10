package com.local.sdp.REST;

import com.local.sdp.Entity.Task;
import com.local.sdp.Services.Interface.GroupServiceInterface;
import com.local.sdp.Services.Interface.TaskCustomServiceInterface;
import com.local.sdp.Services.Interface.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskRESTController {
    @Autowired
    private TaskServiceInterface taskServiceInterface;

    @Autowired
    private TaskCustomServiceInterface taskCustomServiceInterface;
    @Autowired
    private GroupServiceInterface groupServiceInterface;
    @GetMapping("/{groupId}")
    List<Task> allTaskByGroupId(@PathVariable int groupId){
        return taskCustomServiceInterface.findTaskByGroupId(groupId);
    }
    @PostMapping("/{groupId}")
    ResponseEntity<String> setTaskToGroup(@PathVariable int groupId, @RequestBody Task task){
        task.setGroup(groupServiceInterface.getGroupById(groupId));
        taskServiceInterface.save(task);
        return new ResponseEntity<>("Task Saved Successfully", HttpStatus.OK);
    }
}
