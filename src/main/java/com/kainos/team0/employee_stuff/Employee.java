package com.kainos.team0.employee_stuff;

public class Employee {
    private String name;
    private String address;
    private String ni;
    private String iban;
    private String bic;
    private int salary;
    private String employeeNumber;
    //private Deparment deparment;


    public Employee(String name, String address, String iban,
                    String bic, int salary, String employeeNumber){
        this.setName(name);
        this.setAddress(address);
        this.setNi(getNi());
        this.setIban(iban);
        this.setSalary(salary);
        this.setEmployeeNumber(employeeNumber);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNi() {
        return ni;
    }

    public void setNi(String ni) {
        this.ni = ni;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    /*
    public Department getDepartment(){
        return department;
    }

    public void setDepartment(Department department){
        this.department = department;
    }
    */
}
