package com.csi.dao;

import com.csi.model.Employee;
import com.csi.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDaoImpl {

    @Autowired
    EmployeeRepo employeeRepoImpl;

    public Employee signUp(Employee employee) {
        return employeeRepoImpl.save(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag = false;

        Employee employee = employeeRepoImpl.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);
        if (employee != null) {
            flag = true;
        }
        return flag;

    }

    public Optional<Employee> getDataById(long empId){
    return employeeRepoImpl.findById(empId);

    }

    public List<Employee>getAllData(){
        return employeeRepoImpl.findAll();
    }

    public List<Employee>getDataByName(String empFirstName){
        return employeeRepoImpl.findByEmpFirstName(empFirstName);
    }
    public List<Employee>getDataByEmpFirstNameAndEmpLastName(String empFirstName,String empLastName){
        return employeeRepoImpl.findByEmpFirstNameAndEmpLastName(empFirstName,empLastName);
    }

    public Employee getDataByContactNumber(long empContactNumber){

        return employeeRepoImpl.findByEmpContactNumber(empContactNumber);
    }
    public Employee getDataByEmailId(String empEmailId){

        return employeeRepoImpl.findByEmpEmailId(empEmailId);
    }

    public List<Employee>saveBulkOfData(List<Employee> employees){
        return employeeRepoImpl.saveAll(employees);
    }

    public Employee updateData(Employee employee){
        return employeeRepoImpl.save(employee);
    }
    public void deleteDataById(long empId){
        employeeRepoImpl.deleteById(empId);
    }
    public void deleteAllData(){
        employeeRepoImpl.deleteAll();
    }

    public List<Employee> getDataByUsingAnyInput(String input)


     {
        List<Employee>employeeList=new ArrayList<>();

         SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        for (Employee employee:getAllData()){
            String dobDate=simpleDateFormat.format(employee.getEmpDOB());
            if (employee.getEmpFirstName().equals(input)
            ||employee.getEmpLastName().equals(input)
            ||String.valueOf(employee.getEmpContactNumber()).equals(input)
            ||employee.getEmpEmailId().equals(input)
             ||String.valueOf(employee.getEmpId()).equals(input)
            ||dobDate.equals(input)){
                employeeList.add(employee);
            }
        }
        return employeeList;
    }
}
