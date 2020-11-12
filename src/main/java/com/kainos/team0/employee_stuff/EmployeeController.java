package com.kainos.team0.employee_stuff;

import com.kainos.team0.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class EmployeeController {

    private Connection connection;

    public EmployeeController (Connection connection){
        this.connection = connection;
    }

    public String CreateEmployee(String name,
                               String address,
                               String NI,
                               int salary,
                               String IBAN,
                               String BIC,
                               String employeeNumber,
                               int departmentID) {

        String insertString =
                "INSERT INTO Employee(Name, " +
                        "Address, " +
                        "NI, " +
                        "Salary, " +
                        "IBAN, " +
                        "BIC," +
                        "EmployeeNumber," +
                        " DepartmentID) " +
                        "VALUES(?," +
                        "?," +
                        "?, " +
                        "?, " +
                        "?," +
                        "?," +
                        "?," +
                        "?)";
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertString);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, NI);
            preparedStatement.setInt(4, salary);
            preparedStatement.setString(5, IBAN);
            preparedStatement.setString(6, BIC);
            preparedStatement.setString(7, employeeNumber);
            preparedStatement.setInt(8, departmentID);


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                return name;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }




}
