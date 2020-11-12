package com.kainos.team0;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class EmployeeController {

    public static void main(String[] args){
        Connection c = DBConnection.getConnection();
        EmployeeController empC = new EmployeeController(c);
        empC.CreateEmployee("Sam Dowell",
                "Home",
                "PC0000D",
                10_000_00,
                "UK0000000000000",
                "dcasdaca",
                "1");
    }

     private Connection connection;

    public EmployeeController (Connection connection){
        this.connection = connection;
    }

    public void CreateEmployee(String name,
                               String address,
                               String NI,
                               int salary,
                               String IBAN,
                               String BIC,
                               String departmentID
                               ) {

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
        System.out.println(insertString);
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertString);


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, NI);
            preparedStatement.setInt(4, salary);
            preparedStatement.setString(5, IBAN);
            preparedStatement.setString(6, BIC);
            preparedStatement.setString(7, genEmployeeNumber());
            preparedStatement.setString(8, departmentID);


            int rowsAffected = preparedStatement.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



        private String genEmployeeNumber(){
        return "1";
    }
}
