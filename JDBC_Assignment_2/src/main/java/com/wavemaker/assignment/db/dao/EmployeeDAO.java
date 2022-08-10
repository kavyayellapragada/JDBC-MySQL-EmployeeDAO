package com.wavemaker.assignment.db.dao;
import com.wavemaker.assignment.db.connection.MySQLConnectionUtility;
import com.wavemaker.assignment.db.model.Employee;

import java.util.*;
import java.sql.*;

public class EmployeeDAO {

    Connection connection = MySQLConnectionUtility.getConnection();

    public List<Employee> getListOfEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            while(resultSet.next()) {
                String id = resultSet.getString("employee_id");
                String first_name = resultSet.getString("firstname");
                String last_name = resultSet.getString("lastname");
                String dob = resultSet.getString("DOB");
                String company_name = resultSet.getString("companyname");
                String blood_group = resultSet.getString("bloodgroup");
                employeeList.add(new Employee(id, first_name, last_name, dob, company_name, blood_group));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void createEmployee(String ID, String first_name, String last_name,
                               String date_of_birth, String company_name, String blood_group) throws SQLException {
        PreparedStatement prep = connection.prepareStatement("INSERT INTO employee " +
                "(employee_id,firstname,lastname,DOB,companyname,bloodgroup) VALUES (?,?,?,?,?,?)");
        prep.setString(1,ID);
        prep.setString(2,first_name);
        prep.setString(3,last_name);
        prep.setString(4,date_of_birth);
        prep.setString(5,company_name);
        prep.setString(6,blood_group);
        if(prep.executeUpdate()==1){
            System.out.println("Data Inserted Successfully in Employee table");
        }
        else{
            System.out.println("Failed to insert into Employee table");
        }
    }

    public void updateEmployeeDetails(String ID, String first_name, String last_name,
                                      String date_of_birth, String company_name, String blood_group) throws SQLException {
        PreparedStatement prep = connection.prepareStatement("UPDATE employee " +
                "SET firstname=?,lastname=?,DOB=?,companyname=?,bloodgroup=? WHERE employee_id="+ID);
        //prep.setString(1,ID);
        prep.setString(1,first_name);
        prep.setString(2,last_name);
        prep.setString(3,date_of_birth);
        prep.setString(4,company_name);
        prep.setString(5,blood_group);
        int code = prep.executeUpdate();
        if(code == 1){
            System.out.println("Employee details updated successfully");
        }
        else{
            System.out.println("Failed to update employee details");
        }
    }

    public void updateEmployeeDepartment(String ID, String dept_ID, String dept_name, String dept_domain) throws SQLException {
        PreparedStatement prep = connection.prepareStatement("UPDATE department " +
                "SET dept_id=?,name=?,domain=? WHERE employee_id="+ID);
        prep.setString(1,dept_ID);
        prep.setString(2,dept_name);
        prep.setString(3,dept_domain);
        int code = prep.executeUpdate();
        if(code == 1){
            System.out.println("Employee Department details updated successfully");
        }
        else{
            System.out.println("Failed to update employee department details");
        }
    }
    public void updateEmployeeAddress(String ID, String door_no, String street_name, String city_name,
                                      String state_name, String pin_code, String landmark) throws SQLException {
        PreparedStatement prep = connection.prepareStatement("UPDATE address " +
                "SET doornum=?,streetname=?,cityname=?,statename=?,pincode=?,landmark=? WHERE employee_id="+ID);
        prep.setString(1,door_no);
        prep.setString(2,street_name);
        prep.setString(3,city_name);
        prep.setString(4,state_name);
        prep.setString(5,pin_code);
        prep.setString(6,landmark);
        int code = prep.executeUpdate();
        if(code == 1){
            System.out.println("Employee details updated successfully");
        }
        else{
            System.out.println("Failed to update employee details");
        }

    }
    public void deleteEmployeeDetails(String ID) throws SQLException {
        Statement stmt = connection.createStatement();
        int code = stmt.executeUpdate("DELETE FROM employee WHERE employee_id="+ID);
        if(code == 1){
            System.out.println("Employee details with ID "+ID+" deleted successfully");
        }
        else{
            System.out.println("Failed to delete record "+ID+" from employee table");
        }
    }
}
