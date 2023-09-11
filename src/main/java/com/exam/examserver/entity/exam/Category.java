package com.exam.examserver.entity.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long cid;
    private String title;
    private String description;

    @OneToMany(mappedBy = "category", fetch =FetchType.EAGER, cascade = CascadeType.ALL) //category column will be made in Quiz table
    @JsonIgnore
    private Set<Quiz> quizzes=new LinkedHashSet<>(); //order as per insert
}
