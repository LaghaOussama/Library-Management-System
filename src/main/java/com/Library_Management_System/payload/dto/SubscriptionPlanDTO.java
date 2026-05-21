package com.Library_Management_System.payload.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlanDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true,nullable = false)
    private String planCode;

    @Column(length=100,nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer durationDays;

    @Column(nullable = false)
    private Long price;

    private String currency="CHF";

    @Positive(message = "MAx Books must be positive")
    @Column(nullable = false)
    private Integer maxBooksAllowed;


    @Column(nullable = false)
    @Positive(message = "max days must be positive")
    private Integer maxDaysPerBook;

    private Integer displayOrder=0;

    private Boolean isActive=true;
    private Boolean isFeatured=false;

    private String badgeText;
    private String adminNotes;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;
}
