package com.local.sdp.REST;


import com.local.sdp.Entity.Faculty;
import com.local.sdp.ExceptionHandlers.ExceptionResponse;
import com.local.sdp.Services.Interface.FacultyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FacultyRESTController {

    FacultyServiceInterface facultyServiceInterface;
    @Autowired
    FacultyRESTController(FacultyServiceInterface facultyServiceInterface){
        this.facultyServiceInterface = facultyServiceInterface;
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage("Faculty Exception : " + ex.getMessage());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/faculty")
    List<Faculty> getFaculties(){
        return facultyServiceInterface.getFaculties();
    }

    @GetMapping("/faculty/{id}")
    Faculty getFacultyById(@PathVariable int id){
        return facultyServiceInterface.getFacultyById(id);
    }

    @GetMapping("/faculty/userId/{id}")
    Faculty getFacultyByUserId(@PathVariable int id){
        return facultyServiceInterface.getFacultyByUserId(id);
    }

    @PostMapping("/faculty")
    void saveOrUpdate(@RequestBody Faculty faculty){
        facultyServiceInterface.save(faculty);
    }
}
