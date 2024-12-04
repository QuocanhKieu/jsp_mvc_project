package com.example.t2305m_wcd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    private Long id;
    private String subjectName;
    private String description;
    private List<ClassEntity> classes; // Many-to-Many relationship with ClassEntity
}
