package com.kainos.team0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader reader;

    public static void main(String[] args) {
        // System.out.println(DBConnection.testConnection());

        // Read and Parse input from the user and display out to the user

        // setup
        reader = new BufferedReader(new InputStreamReader(System.in));


        write("Team 0 HR");

        while (true) {
            // 1. Enter employee details
            // 2. Generate report per department
            write("\nPlease select an option:");
            write("1. Enter new employee");
            write("2. Generate new department report");
            write("3. Exit");

            String response = readLine();

            try {
                int selection = Integer.parseInt(response);
                switch (selection) {
                    case 1:
                        requestEmployee();
                        break;
                    case 2:
                        requestDepartment();
                        break;
                    case 3:
                        System.exit(0);
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
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private static String readLine() {
        return readLine(null);
    }

    private static void requestEmployee() {
        write("This function enters a new employee into the system.\nEnter '#' to cancel.");
        String number = readLine("Enter Employee ID number:");
        if (number.equals("#")) {
            return;
        }
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
        if (salary.equals("#")) {
            return;
        }

        // pass to controller
    }

    private static void requestDepartment() {
        write("This generates an employee report for a department.\nEnter '#' to cancel.");
        String name = readLine("Enter Department ID number:");

        // pass to controller
    }
}
