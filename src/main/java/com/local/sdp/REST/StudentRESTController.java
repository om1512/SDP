package com.local.sdp.REST;


import com.local.sdp.Entity.Student;
import com.local.sdp.ExceptionHandlers.ExceptionResponse;
import com.local.sdp.Services.Interface.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentRESTController {
    StudentServiceInterface studentServiceInterface;

    @Autowired
    StudentRESTController(StudentServiceInterface studentServiceInterface){
        this.studentServiceInterface = studentServiceInterface;
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage("Student Exception : " + ex.getMessage());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @GetMapping("")
    List<Student> getStudents(){
        return studentServiceInterface.findAll();
    }

    @GetMapping("/{id}")
    Student getStudentById(@PathVariable int id){
        return studentServiceInterface.findById(id);
    }

    @PostMapping("")
    void addStudent(@RequestBody Student student){
        studentServiceInterface.save(student);
    }

    @GetMapping("/userId/{id}")
    Student getStudentByUserId(@PathVariable int id){
        return studentServiceInterface.findByUserId(id);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int id){
        studentServiceInterface.deleteById(id);
    }
}
