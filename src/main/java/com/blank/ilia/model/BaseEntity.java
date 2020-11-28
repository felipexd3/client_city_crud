package com.blank.ilia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdAt;
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;
    @JsonIgnore
    private LocalDateTime removedAt;
    private UUID uuid;

    @PrePersist
    public void generateUuid() {
        uuid = UUID.randomUUID();
    }
}
