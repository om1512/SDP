package com.local.sdp.REST;

import com.local.sdp.Entity.Domain;
import com.local.sdp.Entity.Faculty;
import com.local.sdp.ExceptionHandlers.ExceptionResponse;
import com.local.sdp.Services.Interface.DomainServiceInterface;
import com.local.sdp.Services.Interface.FacultyServiceInterface;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/domain")
@CrossOrigin(origins = "http://localhost:4200")

public class DomainRESTController {
    @Autowired
    DomainServiceInterface domainServiceInterface;

    @Autowired
    FacultyServiceInterface facultyServiceInterface;

    @PostMapping("")
    ResponseEntity<String> save(@RequestBody Domain domain){
        domainServiceInterface.save(domain);
        return new ResponseEntity<>("domain stored successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Integer> delete(@PathVariable int id){
        return new ResponseEntity<>(domainServiceInterface.delete(id), HttpStatus.OK);
    }

    @GetMapping("")
    ResponseEntity<List<Domain>> domainList(){
        return new ResponseEntity<>(domainServiceInterface.getDomain(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Domain> domain(@PathVariable int id){
        return new ResponseEntity<>(domainServiceInterface.getDomainById(id), HttpStatus.OK);
    }

    @PostMapping("/map/{facEmail}/{domainId}")
    ResponseEntity<String> map(@PathVariable String facEmail, @PathVariable int domainId){
        Domain domain = domainServiceInterface.getDomainById(domainId);
        Faculty faculty = facultyServiceInterface.getFacultyByEmail(facEmail);
        Set<Faculty> faculties = domain.getFacultyDomainSet();
        faculties.add(faculty);
        domain.setFacultyDomainSet(faculties);
        domainServiceInterface.save(domain);
        return new ResponseEntity<>("mapped successfully", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage("Domain Exception : " + ex.getMessage());
        exceptionResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
