package com.local.sdp.REST;


import com.local.sdp.Entity.Faculty;
import com.local.sdp.Entity.FacultyChoice;
import com.local.sdp.Entity.Group;
import com.local.sdp.Services.Interface.FacultyChoiceServiceInterface;
import com.local.sdp.Services.Interface.FacultyServiceInterface;
import com.local.sdp.Services.Interface.GroupServiceInterface;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.*;

@RestController
@RequestMapping("/api")
public class FacultyChoiceController {
    @Autowired
    FacultyChoiceServiceInterface facultyChoiceInterface;

    @Autowired
    GroupServiceInterface groupServiceInterface;

    @Autowired
    FacultyServiceInterface facultyServiceInterface;

    @PostMapping("/facultyChoice/{groupId}/{facultyId}")
    ResponseEntity<String> addFacultyChoice(@PathVariable int groupId, @PathVariable int facultyId){
        Group group = groupServiceInterface.getGroupById(groupId);
        Faculty faculty = facultyServiceInterface.getFacultyById(facultyId);
        FacultyChoice facultyChoice = new FacultyChoice();
        facultyChoice.setGroup(group);
        facultyChoice.setFaculty(faculty);
        facultyChoice.setPriority(facultyChoiceInterface.getAllFacultyChoiceByGroup(groupId).size() + 1);
        facultyChoiceInterface.save(facultyChoice);
        return new ResponseEntity<>("Choice Filled Success", HttpStatus.OK);
    }


    @PostMapping("/facultyChoice/changePriority/{groupId}/{facultyId}/{priority}")
    ResponseEntity<String> changePriority(@PathVariable int groupId, @PathVariable int facultyId, @PathVariable int priority){
        FacultyChoice facultyChoice = facultyChoiceInterface.getFacultyChoiceById(groupId, facultyId);
        if(facultyChoice == null) return new ResponseEntity<>("Faculty does not exist", HttpStatus.NOT_FOUND);
        List<FacultyChoice> allChoice = facultyChoiceInterface.getAllFacultyChoiceByGroup(groupId);
        for(FacultyChoice f : allChoice){
            if(priority < facultyChoice.getPriority() && f.getPriority() >= priority && f.getPriority() < facultyChoice.getPriority()){
                f.setPriority(f.getPriority() + 1);
            }else if(priority > facultyChoice.getPriority() && f.getPriority() <= priority && f.getPriority() > facultyChoice.getPriority()){
                f.setPriority(f.getPriority() - 1);
            }
        }
        facultyChoice.setPriority(priority);
        facultyChoiceInterface.save(facultyChoice);
        return new ResponseEntity<>("Priority changed successfully", HttpStatus.OK);
    }

    @GetMapping("/facultyChoice")
    ResponseEntity<List<FacultyChoice>> getAllFaculties(){
        return new ResponseEntity<>(facultyChoiceInterface.getAll(), HttpStatus.OK);
    }

    @GetMapping("/facultyChoice/{id}")
    ResponseEntity<FacultyChoice> getById(@PathVariable int id){
        return new ResponseEntity<>(facultyChoiceInterface.getFacultyChoiceById(id), HttpStatus.OK);
    }


    @PostMapping("/facultyChoice/assignFaculty")
    ResponseEntity<String> assignFaculty(){
        List<Group> groups = groupServiceInterface.groupList();
        List<Group> sortedGroups = sortGroups(groups).getBody();
        int maxGroupsAllow = Math.ceilDiv(groups.size(), facultyServiceInterface.getFaculties().size());
        // key : faculty_id, value : number of groups that faculty is leading
        HashMap<Integer, Integer> faculty = new HashMap<>();
        for(Group group : sortedGroups){
            Map<Integer, Integer> guideMap = new HashMap<>();
            List<FacultyChoice> facultyChoicesOfGroup = facultyChoiceInterface.getAllFacultyChoiceByGroup(group.getId());
            for(FacultyChoice facultyChoice : facultyChoicesOfGroup){
                guideMap.put(facultyChoice.getPriority(), facultyChoice.getFaculty().getId());
            }
            List<Integer> priorities = new ArrayList<>(guideMap.keySet());
            Collections.sort(priorities);
            int i = 0;
            while(i < priorities.size()){
                if(faculty.getOrDefault(facultyServiceInterface.getFacultyById(guideMap.get(priorities.get(i))).getId(), 0) < maxGroupsAllow){
                    group.setFaculty(facultyServiceInterface.getFacultyById(guideMap.get(priorities.get(i))));
                    groupServiceInterface.save(group);
                    faculty.put(guideMap.get(priorities.get(i)), faculty.getOrDefault(guideMap.get(priorities.get(i)), 0) + 1);
                    break;
                }
                i++;
            }
        }

        return new ResponseEntity<>("Faculty assigned to all the groups", HttpStatus.OK);
    }

    private ResponseEntity<List<Group>> sortGroups(List<Group> groups) {
        Map<Integer, Integer> map = new HashMap<>();
        for(Group group : groups){
            map.put(group.getRank(), group.getId());
        }
        List<Integer> employeeByKey = new ArrayList<>(map.keySet());
        Collections.sort(employeeByKey);
        List<Group> ans = new ArrayList<>();
        for(int i:employeeByKey){
            ans.add(groupServiceInterface.getGroupById(map.get(i)));
        }

        return new ResponseEntity<>(ans, HttpStatus.OK);
    }
}
