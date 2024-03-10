package com.local.sdp.REST;


import com.local.sdp.Entity.Faculty;
import com.local.sdp.Entity.User;
import com.local.sdp.ExceptionHandlers.ExceptionResponse;
import com.local.sdp.Services.Interface.FacultyServiceInterface;
import com.local.sdp.Services.Interface.UserServiceInterface;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
@CrossOrigin(origins = "http://localhost:4200")
public class FacultyRESTController {

    FacultyServiceInterface facultyServiceInterface;

    UserServiceInterface userServiceInterface;
    @Autowired
    FacultyRESTController(FacultyServiceInterface facultyServiceInterface, UserServiceInterface userServiceInterface){
        this.facultyServiceInterface = facultyServiceInterface;
        this.userServiceInterface  = userServiceInterface;
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage("Faculty Exception : " + ex.getMessage());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("")
    ResponseEntity<List<Faculty>> getFaculties(){
        return new ResponseEntity<>(facultyServiceInterface.getFaculties(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Faculty> getFacultyById(@PathVariable int id){
        return new ResponseEntity<>(facultyServiceInterface.getFacultyById(id),  HttpStatus.OK);
    }

    @GetMapping("/userId/{id}")
    ResponseEntity<Faculty> getFacultyByUserId(@PathVariable int id){
        return new ResponseEntity<>(facultyServiceInterface.getFacultyByUserId(id), HttpStatus.OK);
    }


    @GetMapping("/email/{email}")
    ResponseEntity<Faculty> getFacultyByEmail(@PathVariable String email){
        return new ResponseEntity<>(facultyServiceInterface.getFacultyByEmail(email), HttpStatus.OK);
    }


    @PostMapping("")
    ResponseEntity<String> saveOrUpdate(@RequestBody Faculty faculty){
        userServiceInterface.save(faculty.getUser());
        User user = userServiceInterface.findByEmail(faculty.getUser().getEmail());
        faculty.setUser(user);
        facultyServiceInterface.save(faculty);
        return new ResponseEntity<>("Faculty saved or updated.",HttpStatus.OK);
    }
}
    