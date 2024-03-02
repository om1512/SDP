package com.local.sdp.REST;

import com.local.sdp.Entity.Phase;
import com.local.sdp.Services.Interface.PhaseCustomServiceInterface;
import com.local.sdp.Services.Interface.PhaseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/phase")
@CrossOrigin(origins = "http://localhost:4200")
public class PhaseRESTController {

    private final PhaseServiceInterface phaseServiceInterface;
    private final PhaseCustomServiceInterface phaseCustomServiceInterface;
    @Autowired
    public PhaseRESTController(PhaseServiceInterface phaseServiceInterface, PhaseCustomServiceInterface phaseCustomServiceInterface) {
        this.phaseServiceInterface = phaseServiceInterface;
        this.phaseCustomServiceInterface = phaseCustomServiceInterface;

    }

    @PostMapping("")
    public ResponseEntity<String> savePhase(@RequestBody Phase phase){
        phaseServiceInterface.save(phase);
        return new ResponseEntity<>("Saved Successfully", HttpStatus.OK);
    }

    @GetMapping("")
    public List<Phase> getAllPhase(){
        return phaseServiceInterface.findAll();
    }

    @GetMapping("/{id}")
    public Phase getPhaseById(@PathVariable int id){
       Optional<Phase> phase = phaseServiceInterface.findById(id);
        return phase.orElse(null);
    }

}
