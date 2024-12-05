package com.example.t2305m_wcd.dao.factory;

import com.example.t2305m_wcd.dao.ClassDAO;
import com.example.t2305m_wcd.dao.StudentDAO;
import com.example.t2305m_wcd.dao.SubjectDAO;
import com.example.t2305m_wcd.dao.impl.ClassDAOImpl;
import com.example.t2305m_wcd.dao.impl.StudentDAOImpl;
import com.example.t2305m_wcd.dao.impl.SubjectDAOImpl;

import java.sql.Connection;

public class DAOFactory {

    public static ClassDAO getClassDAO(Connection connection) {
        return new ClassDAOImpl(connection);
    }

    public static StudentDAO getStudentDAO(Connection connection) {
        return new StudentDAOImpl(connection);
    }

    public static SubjectDAO getSubjectDAO(Connection connection) {
        return new SubjectDAOImpl(connection);
    }
}


//package com.example.t2305m_wcd.dao.factory;
//
//import com.example.t2305m_wcd.dao.StudentDAO;
//import com.example.t2305m_wcd.dao.SubjectDAO;
//import com.example.t2305m_wcd.dao.ClassDAO;
//import com.example.t2305m_wcd.dao.impl.StudentDAOImpl;
//import com.example.t2305m_wcd.dao.impl.SubjectDAOImpl;
//import com.example.t2305m_wcd.dao.impl.ClassDAOImpl;
//
//import java.sql.Connection;
//
//public class DAOFactory {
//    private static StudentDAO studentDAO;
//    private static SubjectDAO subjectDAO;
//    private static ClassDAO classDAO;
//
//    // Private constructor to prevent instantiation
//    private DAOFactory() {}
//
//    public static StudentDAO getStudentDAO(Connection connection) {
//        if (studentDAO == null) {
//            synchronized (DAOFactory.class) {
//                if (studentDAO == null) {
//                    studentDAO = new StudentDAOImpl(connection);
//                }
//            }
//        }
//        return studentDAO;
//    }
//
//    public static SubjectDAO getSubjectDAO(Connection connection) {
//        if (subjectDAO == null) {
//            synchronized (DAOFactory.class) {
//                if (subjectDAO == null) {
//                    subjectDAO = new SubjectDAOImpl(connection);
//                }
//            }
//        }
//        return subjectDAO;
//    }
//
//    public static ClassDAO getClassDAO(Connection connection) {
//        if (classDAO == null) {
//            synchronized (DAOFactory.class) {
//                if (classDAO == null) {
//                    classDAO = new ClassDAOImpl(connection);
//                }
//            }
//        }
//        return classDAO;
//    }
//}
