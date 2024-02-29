package com.local.sdp.REST;


import com.local.sdp.Entity.User;
import com.local.sdp.ExceptionHandlers.ExceptionResponse;
import com.local.sdp.Services.Interface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")

public class UserRESTController {
    UserServiceInterface userService;

    @Autowired
    public UserRESTController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/id/{userId}")
    public User findById(@PathVariable int userId) {
        return userService.findById(userId);
    }

    @GetMapping("/users/email/{userEmail}")
    public User findByEmail(@PathVariable String userEmail) {
        return userService.findByEmail(userEmail);
    }

    @GetMapping("/users/type/{userType}")
    public List<User> findByType(@PathVariable String userType) {
        return userService.findByType(userType);
    }

    @PostMapping("/users")
    public String save(@RequestBody User user) {
        userService.save(user);
        return "Saved";
    }

    @PutMapping("/users")
    public String update(@RequestBody User user) {
        userService.save(user);
        return "Updated";
    }

    @DeleteMapping("/users/{userId}")
    public String delete(@PathVariable int userId) {
        User user = userService.findById(userId);
        userService.delete(userId);
        return "Deleted";
    }


    // Exception Handling

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage("User Exception : " + ex.getMessage());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
