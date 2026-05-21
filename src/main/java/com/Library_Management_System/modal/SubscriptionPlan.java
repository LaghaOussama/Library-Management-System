package com.Library_Management_System.modal;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Plan code is mandatory")
    private String planCode;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @NotNull(message = "Duration is mandatory")
    @Positive(message = "Duration must be positive")
    private Integer durationDays;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private Long price;

    private String currency;

    @Positive(message = "MAx Books must be positive")
    @NotNull(message = "Max books is mandatory")
    private Integer maxBooksAllowed;


    @NotBlank(message = "Max days per book is mandatory")
    @Positive(message = "max days must be positive")
    private Integer maxDaysPerBook;

    private Integer displayOrder;

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
