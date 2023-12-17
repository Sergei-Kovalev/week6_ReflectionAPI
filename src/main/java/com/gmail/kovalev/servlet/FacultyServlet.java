package com.gmail.kovalev.servlet;

import com.gmail.kovalev.controller.Controller;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "faculty", urlPatterns = {"/faculty"})
public class FacultyServlet extends HttpServlet {

    Controller controller;

    @Override
    public void init(ServletConfig config) {
        controller = new Controller();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uuidString = req.getParameter("uuid");
        String facultyById = controller.findFacultyById(uuidString);
        resp.getWriter().println(facultyById);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String facultyDTOJSON = convertInputStreamToString(inputStream);
        String message = controller.saveFaculty(facultyDTOJSON);
        resp.getWriter().println(message);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uuidString = req.getParameter("uuid");
        ServletInputStream inputStream = req.getInputStream();
        String facultyDTOJSON = convertInputStreamToString(inputStream);
        String message = controller.updateFaculty(uuidString, facultyDTOJSON);
        resp.getWriter().println(message);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uuidString = req.getParameter("uuid");
        String message = controller.deleteFacultyByUUID(uuidString);
        resp.getWriter().println(message);
    }

    public static String convertInputStreamToString(ServletInputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
