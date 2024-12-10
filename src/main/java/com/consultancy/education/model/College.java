package com.consultancy.education.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "colleges")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class College {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String campus;

    @Column(name = "website_url")
    String websiteUrl;

    @Column(name = "college_logo")
    String collegeLogo;

    @Column(name = "country")
    String country;

    @Column(name = "established_year", nullable = false)
    Integer establishedYear;

    @Column(name = "ranking")
    Integer ranking;

    @Column(name = "study_level", nullable = false)
    String studyLevel;

    @Column(name = "ielts_min_score")
    Double ieltsMinScore;

    @Column(name = "toefl_min_score")
    Double toeflMinScore;

    @Column(name = "pte_min_score")
    Double pteMinScore;

    @Column(name = "gre_min_score")
    Double greMinScore;

    @Column(name = "gmat_min_score")
    Double gmatMinScore;

    @Column(name = "min_10th_score")
    Double min10thScore;

    @Column(name = "min_inter_score")
    Double minInterScore;

    @Column(name = "min_graduation_score")
    Double minGraduationScore;

    @Column(name = "scholarship_eligible")
    String scholarshipEligible;

    @Column(name = "scholarship_details")
    String scholarshipDetails;

    @Column(name = "backlog_acceptance_range")
    Integer backlogAcceptanceRange;

    @Column(name = "remarks")
    String remarks;

    @Column(name = "description")
    String description;

    @Column(name = "campus_gallery_video_link")
    String campusGalleryVideoLink;

    @Column(name = "eligibility_criteria")
    String eligibilityCriteria;

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @JoinColumn
    @OneToOne
    Seo seo;

    @OneToOne(mappedBy = "college", cascade = CascadeType.ALL, orphanRemoval = true)
    Address address;

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CollegeCourse> collegeCourses;

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
