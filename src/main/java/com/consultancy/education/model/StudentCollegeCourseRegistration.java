package com.consultancy.education.model;

import com.consultancy.education.enums.ApprovalStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_college_course_registration") // Proper table name
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class StudentCollegeCourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status")
    ApprovalStatus applicationStatus;

    @Column(name = "offer_letter")
    String offerLetter;

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @JoinColumn
    @ManyToOne
    Student student;

    @JoinColumn
    @ManyToOne
    CollegeCourse collegeCourse;

    @OneToOne(mappedBy = "studentCollegeCourseRegistration", cascade = CascadeType.ALL, orphanRemoval = true)
    Scholarship scholarship;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
