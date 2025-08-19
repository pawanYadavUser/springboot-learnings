package com.pawan.hello.helloWorld;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping()
    public List<SoftwareEngineer> getEngineers(){
        return softwareEngineerService.getAllEngineers();
    }

    @GetMapping("{id}")
    public SoftwareEngineer getEngineerById(@PathVariable Integer id){
        return softwareEngineerService.getSoftwareEngineerById(id);
    }

    @PostMapping()
    public void addNewSoftwareEngineer( @RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }

    @DeleteMapping("{id}")
    public void deleteSoftwareEngineer(@PathVariable Integer id){
        softwareEngineerService.deleteSoftwareEngineerById(id);
    }

    @PutMapping("{id}")
    public void updateSoftwareEngineer(@PathVariable Integer id, @RequestBody SoftwareEngineer softwareEngineer){
        softwareEngineerService.updateSoftwareEngineerDetails(id, softwareEngineer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SoftwareEngineer> updatedSoftwareEngineerSelectively(@PathVariable Integer id,
                                                                               @RequestBody SoftwareEngineerDTO softwareEngineerDTO){
        SoftwareEngineer softwareEngineer =  softwareEngineerService.updatedSoftwareEngineerSelectiveFileds(id, softwareEngineerDTO);
        return ResponseEntity.ok(softwareEngineer);
    }
}
