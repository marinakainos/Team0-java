package com.kainos.team0.employee_stuff;

import com.kainos.team0.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeController {

    public static void main(String[] args){
        Connection c = DBConnection.getConnection();
        EmployeeController empC = new EmployeeController(c);
//        empC.CreateEmployee("Sam Dowell2",
//                "Home2",
//                "PC0000E",
//                10_000_00,
//                "UK0000000000000",
//                "dcasdaca",
//                1);

        System.out.println(empC.generateReport());
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
            preparedStatement.setInt(8, departmentID);


            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Map<String, List<String>> generateReport() {
        String sql =
                "SELECT Department.DepartmentName, Employee.Name "
                + "FROM Employee, Department "
                + "where Employee.DepartmentID = Department.DepartmentID "
                + "ORDER BY Department.DepartmentID;";

        Map<String, List<String>> report = new HashMap<>();

        try {
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String department = rs.getString("Department.DepartmentName");
                String employee = rs.getString("Employee.Name");

                if (!report.containsKey(department)) {
                    report.put(department, new ArrayList<>());
                }
                report.get(department).add(employee);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return report;
    }

    private String genEmployeeNumber(){
        return "1";
    }

}
