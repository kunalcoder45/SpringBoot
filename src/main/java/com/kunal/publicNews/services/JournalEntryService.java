package com.kunal.publicNews.services;

import com.kunal.publicNews.entity.JournalEntry;
import com.kunal.publicNews.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    // add entry
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    // get entry
    public List<JournalEntry> getEntries(){
        return journalEntryRepository.findAll();
    }

    // get entry by id
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }


    public JournalEntry updateById(ObjectId id, JournalEntry newEntries) {
        Optional<JournalEntry> journal = journalEntryRepository.findById(id);

        if (journal.isPresent()) {
            JournalEntry old = journal.get();
            if (newEntries.getTitle() != null && !newEntries.getTitle().trim().isEmpty()) {
                old.setTitle(newEntries.getTitle());
            }
            if (newEntries.getContent() != null && !newEntries.getContent().trim().isEmpty()) {
                old.setContent(newEntries.getContent());
            }
            journalEntryRepository.save(old);
            return old;
        }
        return null;
    }

    // delete content using id
    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

}
