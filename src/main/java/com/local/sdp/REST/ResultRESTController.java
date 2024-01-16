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
@RequestMapping("/api")
public class ResultRESTController {
    @Autowired
    ResultServiceInterface resultServiceInterface;

    @Autowired
    StudentServiceInterface studentServiceInterface;

    @PostMapping("/result")
    void save(@RequestBody Result result){
        resultServiceInterface.save(result);
    }

    @DeleteMapping("/result/{id}")
    int delete(@PathVariable int id){
        return resultServiceInterface.delete(id);
    }

    @GetMapping("/result")
    List<Result> resultList(){
        return resultServiceInterface.getResult();
    }

    @GetMapping("/result/{id}")
    Result getResultById(@PathVariable int id){
        return resultServiceInterface.getResultById(id);
    }

    @PostMapping("/result/map/{stuId}/{resId}")
    String mapResultWithStudent(@PathVariable int stuId, @PathVariable int resId){
        Result result = resultServiceInterface.getResultById(resId);
        Student student = studentServiceInterface.findById(stuId);
        Set<Student> students = result.getStudentSet();
        students.add(student);
        result.setStudentSet(students);
        resultServiceInterface.save(result);
        return "Mapped success!";
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
