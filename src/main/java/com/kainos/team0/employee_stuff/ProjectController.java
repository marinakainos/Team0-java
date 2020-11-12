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

<<<<<<< HEAD

    public Map<Project, List<String>> getEmployeesOnProject(){
        String sql =

                "SELECT Project.ProjectID, Project.ProjectName, Employee.Name"
                + " FROM Project, Employee, Assignment"
        + " WHERE Project.ProjectID = Assignment.ProjectID"
        + " AND Employee.EmployeeID = Assignment.EmployeeID"
        + " ORDER BY Project.ProjectName;";

        Map<Project, List<String>> report = new HashMap<>();

        try {
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String projectID = rs.getString("Project.ProjectID");
                String projectName = rs.getString("Project.ProjectName");
                String employee = rs.getString("Employee.Name");

                Project project = new Project(Integer.valueOf(projectID), projectName);

                if (!report.containsKey(project)) {
                    report.put(project, new ArrayList<>());
                }
                report.get(project).add(employee);
=======
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

    public Map<Integer, String> GetProjects() {
        String queryString = "SELECT ProjectID, ProjectName FROM Project";

        Map<Integer, String> projects = new HashMap<>();
        try {
            ResultSet rs = connection.prepareStatement(queryString).executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ProjectID");
                String name = rs.getString("ProjectName");
                projects.put(id, name);
>>>>>>> 20035d206dfcfa4f120a871ad9b49202ce846c67
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

<<<<<<< HEAD
        return report;
    }



=======
        return projects;
    }
>>>>>>> 20035d206dfcfa4f120a871ad9b49202ce846c67
}

