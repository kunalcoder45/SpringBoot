//package com.kunal.publicNews.controller;
//
//
//import com.kunal.publicNews.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController {
//
//    public Map<Long,JournalEntry> journalEntries = new HashMap();
//
//
//    @GetMapping
//    public List<JournalEntry> getAll() {
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping
//    public boolean createEntry (@RequestBody JournalEntry myEntries) {
//        journalEntries.put(myEntries.getId(), myEntries);
//        return true;
//    }
//
//    @GetMapping("id/{myId}")
//    public JournalEntry getJournalEntryById(@PathVariable Long myId){
//        return journalEntries.get(myId);
//    }
//
//    @DeleteMapping("id/{myId}")
//    public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
//        return journalEntries.remove(myId);
//    }
//
//
//    @PutMapping("/id/{id}")
//    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
//        return journalEntries.put(id, myEntry);
//    }
//}





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

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntries(@RequestBody JournalEntry myEntries){
        journalEntries.put(myEntries.getId(), myEntries);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry updateEntriesById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }
}
