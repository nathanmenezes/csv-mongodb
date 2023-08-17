package com.csvteste.repository;

import com.csvteste.model.DynamicDataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DynamicDataRepository extends MongoRepository<DynamicDataEntity, String> {
}
