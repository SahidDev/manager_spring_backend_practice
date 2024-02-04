package com.sahidDev.managerSystem;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

///import com.google.api.services.storage.Storage.BucketAccessControls.List;

import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getEmployeeDetails")
    public Employee getEmployee(@RequestParam String lookUpId) throws InterruptedException, ExecutionException {
        return employeeService.getEmployeeDetails(lookUpId);
    }

    @GetMapping("/getAllEmployeesDetails")
    public List<Employee> getAllEmployeesDetails() throws InterruptedException, ExecutionException {
        return employeeService.getAllEmployeesDetails();
    }

    @PostMapping("/createEmployee")
    public String createEmployee(@RequestBody Employee employee) throws InterruptedException, ExecutionException {
        return employeeService.saveEmployeeDetails(employee);
    }

    @PutMapping("/updateEmployee")
    public String updateEmployee(@RequestBody Employee employee) throws InterruptedException, ExecutionException {

        return employeeService.updateEmployeeDetails(employee);
    }

    @DeleteMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam String lookUpId) {
        return employeeService.deleteEmployee(lookUpId);
    }
}
