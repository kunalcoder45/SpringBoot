package com.kunal.publicNews.controllers;

import com.kunal.publicNews.model.JournalSchema;
import com.kunal.publicNews.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journals/v2")
public class JournalController {

    @Autowired
    private JournalService journalService;

    // Get all entries
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalSchema> all = journalService.getEntries();
        if (all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Add entries
    @PostMapping
    public ResponseEntity <JournalSchema>  createEntries(@RequestBody JournalSchema myEntries){
        try {
            myEntries.setDate(LocalDateTime.now());
            journalService.addEntries(myEntries);
            return new ResponseEntity<>(myEntries, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(myEntries, HttpStatus.BAD_REQUEST);
        }
    }

    // View entry by ID
    @GetMapping("/id/{id}")
    public ResponseEntity <JournalSchema> findById(@PathVariable ObjectId id){
        Optional<JournalSchema> journalSchema = journalService.findById(id);
        if (journalSchema.isPresent()){
            return new ResponseEntity<>(journalSchema.get(), HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(400));
    }

    // Update by ID
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId id, @RequestBody JournalSchema newEntries){
        return journalService.updateById(id, newEntries);
    }

    // Delete by ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable ObjectId id) {
        journalService.deleteById(id);
        return ResponseEntity.ok("Delete successfully");
    }
}
