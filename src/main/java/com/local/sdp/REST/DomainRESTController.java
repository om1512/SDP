package com.local.sdp.REST;

import com.local.sdp.Entity.Domain;
import com.local.sdp.Entity.Faculty;
import com.local.sdp.ExceptionHandlers.ExceptionResponse;
import com.local.sdp.Services.Interface.DomainServiceInterface;
import com.local.sdp.Services.Interface.FacultyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class DomainRESTController {
    @Autowired
    DomainServiceInterface domainServiceInterface;

    @Autowired
    FacultyServiceInterface facultyServiceInterface;

    @PostMapping("/domain")
    void save(@RequestBody Domain domain){
        domainServiceInterface.save(domain);
    }

    @DeleteMapping("/domain/{id}")
    int delete(@PathVariable int id){
        return domainServiceInterface.delete(id);
    }

    @GetMapping("/domain")
    List<Domain> domainList(){
        return domainServiceInterface.getDomain();
    }

    @GetMapping("/domain/{id}")
    Domain domain(@PathVariable int id){
        return domainServiceInterface.getDomainById(id);
    }

    @PostMapping("/domain/map/{facId}/{domainId}")
    void map(@PathVariable int facId, @PathVariable int domainId){
        Domain domain = domainServiceInterface.getDomainById(domainId);
        Faculty faculty = facultyServiceInterface.getFacultyById(facId);
        Set<Faculty> faculties = domain.getFacultyDomainSet();
        faculties.add(faculty);
        domain.setFacultyDomainSet(faculties);
        domainServiceInterface.save(domain);
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
