package com.example.t2305m_wcd.dao.impl;

import com.example.t2305m_wcd.dao.SubjectDAO;
import com.example.t2305m_wcd.entity.ClassEntity;
import com.example.t2305m_wcd.entity.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {
    private final Connection connection;

    public SubjectDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Subject> all() {
        String sql = "SELECT * FROM subjects";
        List<Subject> subjects = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getLong("id"),
                        rs.getString("subject_name"),
                        rs.getString("description"),
                        new ArrayList<>() // Populate classes separately if needed
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    @Override
    public void create(Subject subject) {
        String sql = "INSERT INTO subjects (subject_name, description) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getSubjectName());
            stmt.setString(2, subject.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Subject subject) {
        String sql = "UPDATE subjects SET subject_name = ?, description = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getSubjectName());
            stmt.setString(2, subject.getDescription());
            stmt.setLong(3, subject.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM subjects WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Subject find(Long id) {
        String sql = "SELECT * FROM subjects WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Subject(
                            rs.getLong("id"),
                            rs.getString("subject_name"),
                            rs.getString("description"),
                            new ArrayList<>() // Populate classes separately if needed
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ClassEntity> findClassesBySubjectId(Long subjectId) {
        String sql = "SELECT c.id, c.class_name, c.teacher " +
                "FROM classes c " +
                "JOIN class_subject cs ON c.id = cs.class_id " +
                "WHERE cs.subject_id = ?";
        List<ClassEntity> classes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, subjectId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    classes.add(new ClassEntity(
                            rs.getLong("id"),
                            rs.getString("class_name"),
                            rs.getString("teacher"),
                            new ArrayList<>(), // Populate students separately if needed
                            new ArrayList<>()  // Populate subjects separately if needed
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
