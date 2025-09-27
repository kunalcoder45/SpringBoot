package com.kunal.publicNews.controller;


import com.kunal.publicNews.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    public Map<Long,JournalEntry> journalEntries = new HashMap();


    // get the all items
    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    // add items
    @PostMapping
    public boolean createEntries(@RequestBody JournalEntry myEntries){
        journalEntries.put(myEntries.getId(), myEntries);
        return true;
    }

    // view items using id
    @GetMapping("/id/{id}")
    public JournalEntry readEntriesById(@PathVariable Long id){
        return journalEntries.get(id);
    }

    // update values using id
    @PutMapping("/id/{id}")
    public JournalEntry updateEntriesById(@PathVariable Long id, @RequestBody JournalEntry myEntries){
        return journalEntries.put(id, myEntries);
    }

    // delete content using id
    @DeleteMapping("/id/{id}")
    public boolean deleteEntriesById(@PathVariable Long id){
        return journalEntries.remove(id) != null;
    }
}
