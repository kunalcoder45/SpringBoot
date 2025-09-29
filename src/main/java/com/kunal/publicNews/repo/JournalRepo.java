package com.kunal.publicNews.repo;

import com.kunal.publicNews.model.JournalSchema;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepo extends MongoRepository<JournalSchema, ObjectId> {
}
