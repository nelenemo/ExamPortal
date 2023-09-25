package com.exam.examserver.entity.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long cid;
    private String title;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) //category column will be made in Quiz table
    @JsonIgnore
    private Set<Quiz> quizzes=new LinkedHashSet<>(); //order as per insert
}
