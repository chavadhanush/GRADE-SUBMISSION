package com.ltp.gradesubmission.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotBlank(message = "Subject can't be empty!")
    @NonNull
    @Column(name = "subject")
    private String subject;

    @NotBlank(message = "Code can't be empty!")
    @NonNull
    @Column(name = "code")
    private String code;


    @NotBlank(message = "Description can't be empty!")
    @NonNull
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Grade> grades;

    @ManyToMany
    @JoinTable(name = "course_student",joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"))
    private Set<Student> students;
}
