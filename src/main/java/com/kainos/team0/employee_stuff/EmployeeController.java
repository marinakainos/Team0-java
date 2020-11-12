package com.kainos.team0.employee_stuff;

import com.kainos.team0.DBConnection;
import com.mysql.cj.conf.ConnectionUrlParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeController {

    private Connection connection;

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
        System.out.println(empC.generateDepartments());
    }

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

    public Map<Integer, String> generateDepartments() {
        String sql = "SELECT * from Department;";
        Map<Integer, String> deps = new HashMap<>();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("DepartmentID");
                String name = rs.getString("DepartmentName");
                deps.put(id, name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return deps;
    }

    public List<String> generateGrossPayReport() {
        String sql = "SELECT Name, ROUND((Salary / 12) * 0.75, 2) as GrossPay " +
                "FROM Employee " +
                "UNION " +
                "SELECT Name, ROUND((Salary / 12 + CommissionRate *  TotalSales) * 0.75, 2) as GrossPay " +
                "FROM Employee " +
                "INNER JOIN SalesEmployee USING (EmployeeID);";

        List<String> report = new ArrayList<>();

        try {
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("Name");
                String grossPay = rs.getString("GrossPay");

                report.add(name + ": £" + grossPay);
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
