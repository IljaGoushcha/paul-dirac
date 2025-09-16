package com.myprojects.pauldirac.controllers;

import com.myprojects.pauldirac.entity.Attorney;
import com.myprojects.pauldirac.service.AttorneyService;
import com.myprojects.pauldirac.utils.PatchObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AttorneyRestController {

    AttorneyService attorneyService;
    PatchObjectUtil patchObjectUtil;

    @Autowired
    public AttorneyRestController(AttorneyService attorneyService, PatchObjectUtil patchObjectUtil) {
        this.attorneyService = attorneyService;
        this.patchObjectUtil = patchObjectUtil;
    }

    @GetMapping("/attorneys")
    public List<Attorney> getAttorneys() {
        return attorneyService.findAll();
    }

    @GetMapping("/attorneys/{attorneyId}")
    public Attorney getAttorney(@PathVariable int attorneyId) {
        Attorney attorney = attorneyService.findById(attorneyId);
        return attorney;
    }

    @PostMapping("/attorneys")
    public Attorney addAttorney(@RequestBody Attorney attorney) {
        // Just in case an actual ID was passed, this will force it to be recreated by DB
        attorney.setId(0);
        Attorney newAttorney = attorneyService.save(attorney);
        return newAttorney;
    }

    @PutMapping("/attorneys")
    public Attorney updateAttorney(@RequestBody Attorney attorney) {
        Attorney updatedAttorney = attorneyService.save(attorney);
        return updatedAttorney;
    }

    @DeleteMapping("/attorneys/{attorneyId}")
    public ResponseEntity<String> deleteAttorney(@PathVariable int attorneyId) {
        attorneyService.deleteById(attorneyId);
        return ResponseEntity.ok("Attorney (id=" + attorneyId + ") deleted successfully.");
    }

    @PatchMapping("/attorneys/{attorneyId}")
    public Attorney patchAttorney(@PathVariable int attorneyId, @RequestBody Map<String, Object> patchPayload) {

        // Patch will update just one column in table or field in entity
        Attorney originalAttorney = attorneyService.findById(attorneyId);
        if (originalAttorney == null) {
            throw new RuntimeException("Attorney (id=" + attorneyId + ") not found.");
        }

        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Attorney id (id=" + attorneyId + ") not allowed in request body.");
        }

        Attorney updatedAttorney = patchObjectUtil.applyForAttorneys(patchPayload, originalAttorney);

        Attorney patchedAttorney = attorneyService.save(updatedAttorney);
        return patchedAttorney;
    }

}
