//package com.example.t2305m_wcd.controller;
//
//import com.example.t2305m_wcd.dao.StudentDAO;
//import com.example.t2305m_wcd.entity.Student;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(value = "/student")
//public class StudentController extends HttpServlet {
//    private StudentDAO studentDAO;
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        studentDAO = new StudentDAO_Origin();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        String action = req.getParameter("action")!=null?req.getParameter("action"):"";
//        if(action.equals("create")){
//            renderForm(req,resp);
//            return;
//        }
//        List<Student> list = studentDAO.all();
//        req.setAttribute("students",list);
//        RequestDispatcher rd = req.getRequestDispatcher("student/list.jsp");
//        rd.forward(req,resp);
//    }
//
//    private void renderForm(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        RequestDispatcher rd = req.getRequestDispatcher("student/form.jsp");
//        rd.forward(req,resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        studentDAO.create(new Student(
//                null,
//                req.getParameter("name"),
//                req.getParameter("email"),
//                req.getParameter("address"),
//                req.getParameter("telephone")
//        ));
//        resp.sendRedirect("student");
//    }
//}

package com.example.t2305m_wcd.controller;

import com.example.t2305m_wcd.dao.StudentDAO;
import com.example.t2305m_wcd.dao.factory.DAOFactory;
import com.example.t2305m_wcd.entity.ClassEntity;
import com.example.t2305m_wcd.entity.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(value = "/student")
public class StudentController extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Retrieve the connection from the ServletContext
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        // Use DAOFactory to create the StudentDAO
        studentDAO = DAOFactory.getStudentDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action") != null ? req.getParameter("action") : "";

        switch (action) {
            case "create":
                renderForm(req, resp);
                break;
            default:
                listStudents(req, resp);
                break;
        }
    }

    private void renderForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Fetch available classes for the form dropdown (if required)
        List<ClassEntity> classes = (List<ClassEntity>) getServletContext().getAttribute("classes");
        req.setAttribute("classes", classes);

        RequestDispatcher rd = req.getRequestDispatcher("student/form.jsp");
        rd.forward(req, resp);
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Student> students = studentDAO.all();
        req.setAttribute("students", students);
        RequestDispatcher rd = req.getRequestDispatcher("student/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Long classId = req.getParameter("classId") != null
                    ? Long.parseLong(req.getParameter("classId"))
                    : null;

            ClassEntity classEntity = null;
            if (classId != null) {
                classEntity = new ClassEntity();
                classEntity.setId(classId);
            }

            Student newStudent = new Student(
                    null,
                    req.getParameter("name"),
                    req.getParameter("email"),
                    req.getParameter("address"),
                    req.getParameter("telephone"),
                    classEntity
            );

            studentDAO.create(newStudent);
            resp.sendRedirect("student");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Error creating student: " + e.getMessage());
            renderForm(req, resp);
        }
    }
}
