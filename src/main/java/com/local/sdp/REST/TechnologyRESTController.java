package com.local.sdp.REST;


import com.local.sdp.Entity.Faculty;
import com.local.sdp.Entity.Technologies;
import com.local.sdp.Services.Interface.FacultyServiceInterface;
import com.local.sdp.Services.Interface.TechnologyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TechnologyRESTController {

    @Autowired
    TechnologyServiceInterface technologyServiceInterface;

    @Autowired
    FacultyServiceInterface facultyServiceInterface;

    @PostMapping("/tech")
    String save(@RequestBody Technologies technologies) {
        technologyServiceInterface.save(technologies);
        return "saved successfully";
    }

    @DeleteMapping("/tech/{id}")
    int delete(@PathVariable int id){
        return technologyServiceInterface.delete(id);
    }

    @GetMapping("/tech")
    List<Technologies> getTechnology(){
        return technologyServiceInterface.getTechnology();
    }

    @GetMapping("/tech/{id}")
    Technologies getTechnologyById(@PathVariable int id){
        return technologyServiceInterface.getTechnologyById(id);
    }


    @PostMapping("/tech/map/{facId}/{techId}")
    String mapFacultyToTechnology(@PathVariable(name = "facId") int facId, @PathVariable(name= "techId") int techId){
        Faculty faculty = facultyServiceInterface.getFacultyById(facId);
        Technologies technologies = technologyServiceInterface.getTechnologyById(techId);
        Set<Faculty> set = technologies.getFacultyTechnologySet();
        set.add(faculty);
        technologies.setFacultyTechnologySet(set);
        technologyServiceInterface.save(technologies);
        return "mapped successfully";
    }
}
