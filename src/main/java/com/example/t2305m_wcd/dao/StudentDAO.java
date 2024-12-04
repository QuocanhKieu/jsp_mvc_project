package com.example.t2305m_wcd.dao;

import com.example.t2305m_wcd.entity.Student;
import java.util.List;

public interface StudentDAO extends DAOInterface<Student, Long> {
    // Additional methods for Student-specific queries
    List<Student> findStudentsByClassId(Long classId);
}
