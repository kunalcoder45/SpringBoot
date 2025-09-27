package com.kunal.publicNews.controller;


import com.kunal.publicNews.entity.JournalEntry;
import com.kunal.publicNews.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService  journalEntryService;

    // get the all items
    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getEntries();
    }

    // add items
    @PostMapping
    public JournalEntry createEntries(@RequestBody JournalEntry myEntries) {
        myEntries.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntries);
        return myEntries;
    }

    // view items using id
    @GetMapping("/id/{id}")
    public JournalEntry readEntriesById(@PathVariable ObjectId id) {
        return journalEntryService.findById(id).orElse(null);
    }

    // update values using id
    @PutMapping("/id/{id}")
    public JournalEntry updateEntriesById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntries) {
        return journalEntryService.updateById(id, newEntries);
    }

    // delete content using id
    @DeleteMapping("/id/{id}")
    public boolean deleteEntriesById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
        return true;
    }
}
