package com.kainos.team0;

import com.kainos.team0.employee_stuff.EmployeeController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Map;

public class Main {
    private static BufferedReader reader;
    private static InputStreamReader isr;
    private static EmployeeController ec;

    public static void main(String[] args) {
        // System.out.println(DBConnection.testConnection());

        // Read and Parse input from the user and display out to the user

        write("Team 0 HR");
        // setup
        isr = new InputStreamReader(System.in);
        reader = new BufferedReader(isr);

        write("Connecting... Please wait...");
        Connection c = DBConnection.getConnection();
        ec = new EmployeeController(c);

        write("Connected.");

        while (true) {
            // 1. Enter employee details
            // 2. Generate report per department
            write("\nPlease select an option:");
            write("0. Exit");
            write("1. Enter new employee");
            write("2. Generate new department report");
            write("3. Generate employee gross pay report");
            write("4. Generate highest total sales report");



            String response = readLine();

            try {
                int selection = Integer.parseInt(response);
                switch (selection) {
                    case 0:
                        System.exit(0);
                    case 1:
                        requestEmployee();
                        waitToContinue();
                        break;
                    case 2:
                        requestDepartment();
                        waitToContinue();
                        break;
                    case 3:
                        requestEmployeeGrossPayReport();
                        waitToContinue();
                        break;
                    case 4:
                        requestHighestSales();
                        waitToContinue();
                        break;
                    default:
                        write("Invalid selection");
                }
            } catch (NumberFormatException e) {
                write("Invalid selection.");
            }
        }


    }

    private static void write(String s) {
        System.out.println(s);
    }

    private static String readLine(String s) {
        if (s != null) {
            write(s);
        }
        try {
            System.out.flush();
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private static String readLine() {
        return readLine(null);
    }

    private static void waitToContinue() {
        readLine("\nPress enter key to continue...");
    }

    private static void requestEmployee() {
        // OPTION 1
        write("\nThis function enters a new employee into the system.\nEnter '#' to cancel.");
        String number = readLine("Enter Employee ID number:");
        if (number.equals("#")) {
            return;
        }

        String department = "";
        Map<Integer, String> depts = ec.generateDepartments();
        while(true) {
            write("Enter department ID:");
            // display departments
            for(Integer index: depts.keySet()) {
                write(index + ". " + depts.get(index));
            }
            department = readLine();
            if (!depts.keySet().contains(Integer.parseInt(department))) {
                // not a option
                write("Invalid department number.");
            } else {
                break;
            }
        }

        // int
        if (department.equals("#")) {
            return;
        }

        boolean isSalesEmployee = false;
        String commisionRate = "";
        String salesTotal = "";
        if (department.equals("2")){
            isSalesEmployee = true;
            commisionRate = readLine("Enter commision rate:");
            salesTotal = readLine("Enter sales total:");
        }
        float commisionR = Float.parseFloat(commisionRate);
        int salesT = Integer.parseInt(salesTotal);

        int departmentID = Integer.parseInt(department);
        String name = readLine("Enter Employee name:");
        if (name.equals("#")) {
            return;
        }
        String address = readLine("Enter full address:");
        if (address.equals("#")) {
            return;
        }
        String ni = readLine("Enter NI number");
        if (ni.equals("#")) {
            return;
        }
        String iban = readLine("Enter IBAN number:");
        if (iban.equals("#")) {
            return;
        }
        String bic = readLine("Enter BIC number:");
        if (bic.equals("#")) {
            return;
        }
        String salary = readLine("Enter the base salary (enter '123400' where it is £1234.00)");
        //int
        if (salary.equals("#")) {
            return;
        }
        int salaryInt = Integer.parseInt(salary);

        // pass to controller


        if (isSalesEmployee) {
            String ret = ec.createSalesEmployee(name, address, ni, salaryInt, iban, bic, number, departmentID, commisionR, salesT);
            if (ret != null) {
                write( ret+ " has been added to the system.");
            } else {
                write("Error: The user could not be added.");
            }
        } else {
            String ret = ec.CreateEmployee(name, address, ni, salaryInt, iban, bic, number, departmentID);
            if (ret != null) {
                write( ret+ " has been added to the system.");
            } else {
                write("Error: The user could not be added.");
            }
        }

    }

    private static void requestDepartment() {
        // OPTION 2
        write("\nFull Employee Department Report:");
        //generateReports returns Map<String, List<String>>
        var map = ec.generateReport();
        for(String dept: map.keySet()) {
            write(dept + ": ");
            for(String name: map.get(dept)) {
                write("  - " + name);
            }
        }
    }

    private static void requestEmployeeGrossPayReport() {
        // OPTION 3
        write("\nEmployee Gross Pay Report:");
        for (String result : ec.generateGrossPayReport()) {
            write(result);
        }
    }

    private static void requestHighestSales() {
        // OPTION 4
        write("\nHighest Total Sales Report:");
        write(ec.generateHighestSalesTotalReport());
    }
}
