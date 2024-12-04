package com.example.t2305m_wcd.dao;

import com.example.t2305m_wcd.entity.ClassEntity;
import com.example.t2305m_wcd.entity.Subject;

import java.util.List;

public interface SubjectDAO extends DAOInterface<Subject, Long> {
    List<ClassEntity> findClassesBySubjectId(Long subjectId);
}
