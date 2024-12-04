package com.example.t2305m_wcd.dao;

import com.example.t2305m_wcd.entity.ClassEntity;
import java.util.List;

public interface ClassDAO extends DAOInterface<ClassEntity, Long> {
    // Additional methods for Class-specific queries
    List<ClassEntity> findClassesByTeacherName(String teacherName);
}
