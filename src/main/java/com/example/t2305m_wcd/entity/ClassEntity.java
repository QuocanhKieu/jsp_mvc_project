package com.example.t2305m_wcd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {
    private Long id;
    private String className;
    private String teacher;
    private List<Student> students; // List of students for One-to-Many relationship
    private List<Subject> subjects; // Many-to-Many relationship with Subject
}
