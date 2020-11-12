package com.kainos.team0.employee_stuff;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ProjectController {

    private Connection connection;

    public ProjectController(Connection connection) {
        this.connection = connection;
    }

    public String CreateProject(String projectName) {
        String insertString = "INSERT INTO Project(ProjectName) VALUES(?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertString);

            preparedStatement.setString(1, projectName);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                return projectName;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public boolean AssignEmployeeToProject(String projectID, String employeeID) {
        String insertString = "INSERT INTO Assignment(ProjectID, EmployeeID) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertString);
            preparedStatement.setString(1, projectID);
            preparedStatement.setString(2, employeeID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}

