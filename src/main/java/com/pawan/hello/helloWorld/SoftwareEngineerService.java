package com.pawan.hello.helloWorld;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;
    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository){
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public List<SoftwareEngineer> getAllEngineers(){
        return softwareEngineerRepository.findAll();
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id){
        return softwareEngineerRepository.findById(id).orElseThrow(()->
            new RuntimeException(id+" not found"));
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer){
         softwareEngineerRepository.save(softwareEngineer);
    }

    public void deleteSoftwareEngineerById(Integer id){
        softwareEngineerRepository.deleteById(id);
    }

    public void updateSoftwareEngineerDetails (Integer id, SoftwareEngineer updateDetails){
        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findById(id).orElseThrow(
                ()-> new IllegalIdentifierException(id+" not found")
        );
        softwareEngineer.setName(updateDetails.getName());
        softwareEngineer.setTechStack(updateDetails.getTechStack());
        softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer updatedSoftwareEngineerSelectiveFileds(Integer id, SoftwareEngineerDTO softwareEngineerDTO){
        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findById(id).orElseThrow(
                ()-> new RuntimeException(id+" not found")
        );
        if(softwareEngineerDTO.getName() != null){
            softwareEngineer.setName(softwareEngineerDTO.getName());
        }
        if(softwareEngineerDTO.getTechStack() != null){
            softwareEngineer.setTechStack(softwareEngineerDTO.getTechStack());
        }

        return softwareEngineerRepository.save(softwareEngineer);
    }
}
