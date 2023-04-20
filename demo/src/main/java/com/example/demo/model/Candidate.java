package com.example.demo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private long id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private Date dateOfBirth;
    @Column
    private String contactNumber;
    @Column(nullable = false)
    private String email;
    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Skill> skillList = new HashSet<Skill>();
}

