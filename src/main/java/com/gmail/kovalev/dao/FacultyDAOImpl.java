package com.gmail.kovalev.dao;

import com.gmail.kovalev.config.Config;
import com.gmail.kovalev.entity.Faculty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FacultyDAOImpl implements FacultyDAO {

    private final static String FIND_BY_ID = "SELECT * FROM faculties WHERE id = ?";
    private final static String FIND_ALL = "SELECT * FROM faculties";

    private final static String SAVE_NEW_FACULTY = """
            INSERT INTO faculties(id, name, teacher, actual_visitors, max_visitors, price_per_day)
            VALUES (?, ?, ?, ?, ?, ?);
            """;

    private final static String UPDATE_FACULTY = """
            UPDATE faculties
            SET name = ?, teacher = ?, actual_visitors = ?, max_visitors = ?, price_per_day = ?
            WHERE id = ?;
            """;

    private final static String DELETE_BY_ID = "DELETE FROM faculties WHERE id = ?";

    @Override
    public Faculty findFacultyById(UUID uuid) {
        Faculty faculty = new Faculty();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setObject(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                faculty.setId(UUID.fromString(resultSet.getString("id")));
                faculty.setName(resultSet.getString("name"));
                faculty.setTeacher(resultSet.getString("teacher"));
                faculty.setActualVisitors(resultSet.getInt("actual_visitors"));
                faculty.setMaxVisitors(resultSet.getInt("max_visitors"));
                faculty.setPricePerDay(resultSet.getDouble("price_per_day"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return faculty;
    }

    @Override
    public List<Faculty> findAllFaculties() {
        List<Faculty> allFaculties = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Faculty faculty = new Faculty();
                faculty.setId(UUID.fromString(resultSet.getString("id")));
                faculty.setName(resultSet.getString("name"));
                faculty.setTeacher(resultSet.getString("teacher"));
                faculty.setActualVisitors(resultSet.getInt("actual_visitors"));
                faculty.setMaxVisitors(resultSet.getInt("max_visitors"));
                faculty.setPricePerDay(resultSet.getDouble("price_per_day"));
                allFaculties.add(faculty);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allFaculties;
    }

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        UUID uuid = UUID.randomUUID();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_NEW_FACULTY);
            statement.setObject(1, uuid);
            statement.setString(2, faculty.getName());
            statement.setString(3, faculty.getTeacher());
            statement.setInt(4, faculty.getActualVisitors());
            statement.setInt(5, faculty.getMaxVisitors());
            statement.setDouble(6, faculty.getPricePerDay());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        faculty.setId(uuid);
        return faculty;
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_FACULTY);
            statement.setString(1, faculty.getName());
            statement.setString(2, faculty.getTeacher());
            statement.setInt(3, faculty.getActualVisitors());
            statement.setInt(4, faculty.getMaxVisitors());
            statement.setDouble(5, faculty.getPricePerDay());
            statement.setObject(6, faculty.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return faculty;
    }

    @Override
    public String deleteFacultyByUUID(UUID uuid) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setObject(1, uuid);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "The account with UUID = " + uuid + " has been deleted.";
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(
                Config.getConfig().get("db").get("url"),
                Config.getConfig().get("db").get("login"),
                Config.getConfig().get("db").get("password")
        );
    }
}
