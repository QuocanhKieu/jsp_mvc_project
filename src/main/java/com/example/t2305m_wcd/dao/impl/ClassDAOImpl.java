package com.example.t2305m_wcd.dao.impl;

import com.example.t2305m_wcd.dao.ClassDAO;
import com.example.t2305m_wcd.entity.ClassEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAOImpl implements ClassDAO {
    private final Connection connection;

    public ClassDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ClassEntity> all() {
        String sql = "SELECT * FROM classes";
        List<ClassEntity> classes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                classes.add(new ClassEntity(
                        rs.getLong("id"),
                        rs.getString("class_name"),
                        rs.getString("teacher"),
                        new ArrayList<>(), // Populate students separately if needed
                        new ArrayList<>()  // Populate subjects separately if needed
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public void create(ClassEntity classEntity) {
        String sql = "INSERT INTO classes (class_name, teacher) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, classEntity.getClassName());
            stmt.setString(2, classEntity.getTeacher());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ClassEntity classEntity) {
        String sql = "UPDATE classes SET class_name = ?, teacher = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, classEntity.getClassName());
            stmt.setString(2, classEntity.getTeacher());
            stmt.setLong(3, classEntity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM classes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClassEntity find(Long id) {
        String sql = "SELECT * FROM classes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ClassEntity(
                            rs.getLong("id"),
                            rs.getString("class_name"),
                            rs.getString("teacher"),
                            new ArrayList<>(), // Populate students separately if needed
                            new ArrayList<>()  // Populate subjects separately if needed
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ClassEntity> findClassesByTeacherName(String teacherName) {
        String sql = "SELECT * FROM classes WHERE teacher = ?";
        List<ClassEntity> classes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, teacherName);
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
