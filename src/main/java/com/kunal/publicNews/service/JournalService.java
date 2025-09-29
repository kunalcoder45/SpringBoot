package com.kunal.publicNews.service;

import com.kunal.publicNews.model.JournalSchema;
import com.kunal.publicNews.repo.JournalRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepo journalRepo;

    // Get all entries
    public List<JournalSchema> getEntries(){
        return journalRepo.findAll();
    }

    // Add entries
    public JournalSchema addEntries(JournalSchema journalSchema){
        return journalRepo.save(journalSchema);
    }

    // Find by ID
    public Optional<JournalSchema> findById(ObjectId id){
        return journalRepo.findById(id);
    }

    // Update by ID
    public ResponseEntity<?> updateById(ObjectId id, JournalSchema newEntries){
        Optional<JournalSchema> journal = journalRepo.findById(id);
        if (journal.isPresent()){
            JournalSchema old = journal.get();
            if (newEntries.getTitle() != null && !newEntries.getTitle().trim().isEmpty()){
                old.setTitle(newEntries.getTitle());
            }
            if (newEntries.getDescription() != null && !newEntries.getDescription().trim().isEmpty()){
                old.setDescription(newEntries.getDescription());
            }
            journalRepo.save(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete by ID
    public void deleteById(ObjectId id){
        journalRepo.deleteById(id);
    }
}
