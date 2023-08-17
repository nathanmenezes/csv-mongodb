package com.csvteste.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dynamicData")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DynamicDataEntity {
    @Id
    private String id;
    private org.bson.Document data;

    public DynamicDataEntity(org.bson.Document parse) {
        this.data = parse;
    }
}
