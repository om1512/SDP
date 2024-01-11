package com.local.sdp.REST;


import com.local.sdp.Entity.User;
import com.local.sdp.ExceptionHandlers.ExceptionResponse;
import com.local.sdp.ExceptionHandlers.SpecificExceptions.UserNotFound;
import com.local.sdp.Services.Interface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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
        User user = userService.findById(userId);

        if(user == null) {
            throw  new UserNotFound("User not found " + userId);
        }

        return user;
    }

    @GetMapping("/users/email/{userEmail}")
    public User findByEmail(@PathVariable String userEmail) {
        User user = userService.findByEmail(userEmail);

        if(user == null) {
            throw new UserNotFound("User not found : " + userEmail);
        }

        return user;
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

        if(user == null) {
            throw  new UserNotFound("User not found : " + userId);
        }

        userService.delete(userId);
        return "Deleted";
    }


    // Exception Handling

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> userNotFound(UserNotFound exception) {
        ExceptionResponse error = new ExceptionResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> badRequest(java.lang.Exception exception) {
        ExceptionResponse error = new ExceptionResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
