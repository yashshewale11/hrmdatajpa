package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/signUp")
    public ResponseEntity<Employee>signUp(@Valid @RequestBody Employee employee){
        return new ResponseEntity<>(employeeServiceImpl.signUp(employee),HttpStatus.CREATED);
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
        public ResponseEntity<Boolean>signIn(@PathVariable String empEmailId,@PathVariable String empPassword){

        return  ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId,empPassword));
    }

    @GetMapping("/getdatabyid/{empId}")

    public ResponseEntity<Optional<Employee>>getDataById(@PathVariable long empId){
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }
    @GetMapping("/getdatabyempfirstname/{empFirstName}")

    public ResponseEntity<List<Employee>>getDataByEmpFirstName(@PathVariable String empFirstName){
        return ResponseEntity.ok(employeeServiceImpl.getDataByName(empFirstName));
    }

    @GetMapping("/getdatabyempfirstnameandemplatname/{empFirstName}/{empLastName}")
     public ResponseEntity<List<Employee>>getDataByEmpFirstNameAndEmpLastName(@PathVariable String empFirstName,@PathVariable String empLastName){
        return ResponseEntity.ok(employeeServiceImpl.getDataByEmpFirstNameAndEmpLastName(empFirstName,empLastName));
    }
    @GetMapping("/getdatabyempcontactnumber/{empContactNumber}")
    public ResponseEntity<Employee>getDataByEmpContactNumber(@PathVariable long empContactNumber){
        return ResponseEntity.ok(employeeServiceImpl.getDataByContactNumber(empContactNumber));

    }

    @GetMapping ("/getdatabyempemailid/{empEmailId}")
    public ResponseEntity<Employee>getDataByEmpEmailId(@PathVariable String empEmailId){
        return ResponseEntity.ok(employeeServiceImpl.getDataByEmailId(empEmailId));
    }
    @PostMapping("/savebulkofdata")
    public ResponseEntity<List<Employee>>saveBulkOfData(@RequestBody List<Employee> employees){
        return new ResponseEntity<>(employeeServiceImpl.saveBulkOfData(employees),HttpStatus.CREATED);
    }
    @GetMapping("/getdatabyusinganyinput/{Input}")
    public ResponseEntity<List<Employee>>getDataByUsingAnyInput(@PathVariable String Input){
        return ResponseEntity.ok(employeeServiceImpl.getDataByUsingAnyInput(Input));
    }
    @GetMapping("/sortbyid")

    public ResponseEntity<List<Employee>>sortById(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingLong(Employee::getEmpId)).collect(Collectors.toList()));
    }
    @GetMapping("/sortbyage")
    public ResponseEntity<List<Employee>>sortByAge(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpAge)).collect(Collectors.toList()));
    }
    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>>sortBySalary(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList()));
    }
    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>>sortByFirstName(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpFirstName)).collect(Collectors.toList()));
    }
    @GetMapping("/sortbydob")
    public ResponseEntity<List<Employee>>sortByDOB(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).collect(Collectors.toList()));
    }

    @GetMapping("/filterdatabysalary/empSalary")
    public ResponseEntity<List<Employee>>filterBySalary(@PathVariable double empSalary){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpSalary()>=empSalary).collect(Collectors.toList()));
    }

    @GetMapping("/checkloaneligibility/{empId}")
    public ResponseEntity<String>checkLoanEligibility(@PathVariable long empId) {

        String message = null;
        for (Employee employee : employeeServiceImpl.getAllData()) {
            if (employee.getEmpId() == empId && employee.getEmpSalary() >= 50000.00) {

                message = "Yes! Eligibal For Loan";
                break;
            } else {
                message = "Oops!Not Eligible For Loan";
            }

        }
        return ResponseEntity.ok(message);
    }


    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<List<Employee>>updateData(@PathVariable long empId,@RequestBody Employee employee){
        Employee employee1=employeeServiceImpl.getDataById(empId).orElseThrow(()->new RecordNotFoundException("Employee #Id does not exist"));
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpFirstName(employee.getEmpFirstName());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpAge(employee.getEmpAge());

        return new ResponseEntity (employeeServiceImpl.updateData(employee1),HttpStatus.CREATED);
    }
    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>>getAllData(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData());

    }
    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String>deleteDataByID(@PathVariable long empId){
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data Deleted Succsessfully");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String>deleteAllData(){
        employeeServiceImpl.deleteAllData();
        return  ResponseEntity.ok("Data Deleted Succsessfully");
    }







}
