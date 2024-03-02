package com.local.sdp.REST;

import com.local.sdp.Entity.Result;
import com.local.sdp.Entity.Student;
import com.local.sdp.ExceptionHandlers.ExceptionResponse;
import com.local.sdp.Services.Interface.ResultServiceInterface;
import com.local.sdp.Services.Interface.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/result")
@CrossOrigin(origins = "http://localhost:4200")
public class ResultRESTController {
    @Autowired
    ResultServiceInterface resultServiceInterface;

    @Autowired
    StudentServiceInterface studentServiceInterface;

    @PostMapping("/{stuId}")
    void save(@RequestBody Result result, @PathVariable int stuId){
        Student student = studentServiceInterface.findById(stuId);
        result.setStudent(student);
        resultServiceInterface.save(result);
    }

    @DeleteMapping("/{id}")
    int delete(@PathVariable int id){
        return resultServiceInterface.delete(id);
    }

    @GetMapping("")
    List<Result> resultList(){
        return resultServiceInterface.getResult();
    }

    @GetMapping("/{id}")
    Result getResultById(@PathVariable int id){
        return resultServiceInterface.getResultById(id);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage("Result Exception : " + ex.getMessage());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
