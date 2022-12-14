package com.upthemuscle.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
@Document(collection = "photo")
@Data
public class Photo {
    @Id
    private String id;

    private byte[] photo;
}
