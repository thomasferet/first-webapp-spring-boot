package com.openclassrooms.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.repository.EmployeeProxy;

import lombok.Data;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeProxy employeeProxy;

    public Employee getEmployee(final int id) {
        return employeeProxy.getEmployee(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeProxy.getEmployees();
    }

    public void deleteEmployee(final int id) {
        employeeProxy.deleteEmployee(id);
    }

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee;

        // Règle métier : Le nom de famille doit être converti en majuscules
        employee.setLastName(employee.getLastName().toUpperCase());

        if (employee.getId() == null) {
            // Si l'ID est nul, c'est une création
            savedEmployee = employeeProxy.createEmployee(employee);
        } else {
            // Sinon, c'est une mise à jour
            savedEmployee = employeeProxy.updateEmployee(employee);
        }

        return savedEmployee;
    }

}