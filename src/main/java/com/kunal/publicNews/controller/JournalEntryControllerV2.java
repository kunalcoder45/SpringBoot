package com.kunal.publicNews.controller;


import com.kunal.publicNews.entity.JournalEntry;
import com.kunal.publicNews.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService  journalEntryService;

    // get the all items
    @GetMapping
    public ResponseEntity<?> getAll(){ // data liye
        try {
            List<JournalEntry> all = journalEntryService.getEntries(); // data stored in all
            if (all != null && !all.isEmpty()){ // chcking data
                return new ResponseEntity<>(all,HttpStatus.OK); // yadi data mila toh status OK rahega
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // data nahi mila toh Not found
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }

    // add items
    @PostMapping
    public ResponseEntity<?> createEntries(@RequestBody JournalEntry myEntries) {
        try {
            myEntries.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntries);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added entry");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add entry: " + e.getMessage());
        }
    }

    // view items using id
    @GetMapping("/id/{id}")
    public ResponseEntity<?> readEntriesById(@PathVariable ObjectId id) {
        try {
            Optional<JournalEntry> entry = journalEntryService.findById(id);
            if (entry.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body("Get the entry");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nothing");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error" + e);
        }
    }

    // update values using id
    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateEntriesById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntries) {
       try {
           JournalEntry updateEntry = journalEntryService.updateById(id, newEntries);
           if (updateEntry != null){
               Map<String, Object> response = new HashMap<>();
               response.put("message", "Journal Entry Updated Sucessfully");
               response.put("entry",updateEntry);
               return ResponseEntity.ok(response.toString());
           } else {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
           }
       } catch (Exception e) {
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
       }
    }

    // delete content using id
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEntriesById(@PathVariable ObjectId id) {
        try {
            JournalEntry deletedEntry = journalEntryService.deleteById(id);

            if (deletedEntry != null) {
                return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }
}
