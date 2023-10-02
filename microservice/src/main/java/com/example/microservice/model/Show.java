package com.example.microservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Show", description = "Model of show")
public class Show {
    @Id
    @Schema(name = "ID",
            description = " mongoDb identify",
            example = "45ca551fb8ad658e5ca5d01",
            accessMode = Schema.AccessMode.READ_ONLY)
    String id;
    @Schema(name = "name",
            description = "Name of show",
            example = "Майстер шеф",
            accessMode = Schema.AccessMode.READ_WRITE)
    String name;
    @Schema(name = "description",
            description = "description of show",
            example = "кухонне шоу",
            accessMode = Schema.AccessMode.READ_WRITE)
    String description;
    @Schema(name = "createdAt",
            description = "Date and time of show creation",
            example = "2023-09-25T14:13:52.837",
            accessMode = Schema.AccessMode.AUTO)
    LocalDateTime createdAt;
    @Schema(name = "updatedAt",
            description = "Date and time of show updates",
            example = "2023-09-25T14:13:56.239",
            accessMode = Schema.AccessMode.AUTO)
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
