package com.example.microservice.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "Show")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Show {
    @Id
    String id;
    String name;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public Show(String id,
                String name,
                String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Show(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Show show = (Show) o;
        return Objects.equals(getId(), show.getId());
    }
}
