package com.local.sdp.REST;


import com.local.sdp.Entity.Faculty;
import com.local.sdp.Entity.Technologies;
import com.local.sdp.ExceptionHandlers.ExceptionResponse;
import com.local.sdp.Services.Interface.FacultyServiceInterface;
import com.local.sdp.Services.Interface.TechnologyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tech")
@CrossOrigin(origins = "http://localhost:4200")

public class TechnologyRESTController {

    @Autowired
    TechnologyServiceInterface technologyServiceInterface;

    @Autowired
    FacultyServiceInterface facultyServiceInterface;

    @PostMapping("")
    String save(@RequestBody Technologies technologies) {
        technologyServiceInterface.save(technologies);
        return "saved successfully";
    }

    @DeleteMapping("/{id}")
    int delete(@PathVariable int id){
        return technologyServiceInterface.delete(id);
    }

    @GetMapping("")
    List<Technologies> getTechnology(){
        return technologyServiceInterface.getTechnology();
    }

    @GetMapping("/{id}")
    Technologies getTechnologyById(@PathVariable int id){
        return technologyServiceInterface.getTechnologyById(id);
    }


    @PostMapping("/map/{facId}/{techId}")
    String mapFacultyToTechnology(@PathVariable(name = "facId") int facId, @PathVariable(name= "techId") int techId){
        Faculty faculty = facultyServiceInterface.getFacultyById(facId);
        Technologies technologies = technologyServiceInterface.getTechnologyById(techId);
        Set<Faculty> set = technologies.getFacultyTechnologySet();
        set.add(faculty);
        technologies.setFacultyTechnologySet(set);
        technologyServiceInterface.save(technologies);
        return "mapped successfully";
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage("Technology Exception : " + ex.getMessage());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
