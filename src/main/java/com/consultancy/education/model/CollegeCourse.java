package com.consultancy.education.model;

import com.consultancy.education.enums.CollegeCourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "college_courses") // Proper table name
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CollegeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "intake_month", nullable = false)
    Integer intakeMonth;

    @Column(name = "intake_year", nullable = false)
    Integer intakeYear;

    @Column(name = "tuition_fee")
    Double tuitionFee;

    @Column(name = "application_fee")
    Double applicationFee;

    @Column(name = "duration", nullable = false)
    String duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "application_deadline")
    LocalDate applicationDeadline;

    @Column(name = "max_students")
    Integer maxStudents;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    CollegeCourseStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @JoinColumn
    @ManyToOne
    College college;

    @JoinColumn
    @ManyToOne
    Course course;

    @OneToMany(mappedBy = "collegeCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    List<StudentCollegeCourseRegistration> studentCollegeCourseRegistrations;

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
