package com.sms;

import com.sms.entity.Employee;
import com.sms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        new DemoApplication().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. View Employee by ID");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    viewEmployeeById(scanner);
                    break;
                case 4:
                    updateEmployee(scanner);
                    break;
                case 5:
                    deleteEmployee(scanner);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }

    private void addEmployee(Scanner scanner) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        LocalDate dateOfBirth = parseDate(scanner.nextLine());
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDateOfBirth(dateOfBirth);
        employee.setEmail(email);
        employee.setDepartment(department);

        employeeService.saveEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private void viewAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        employees.forEach(System.out::println);
    }

    private void viewEmployeeById(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        Employee employee = employeeService.getEmployeeById(id);
        System.out.println(employee);
    }

    private void updateEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        Employee existingEmployee = employeeService.getEmployeeById(id);

        System.out.print("Enter First Name (" + existingEmployee.getFirstName() + "): ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name (" + existingEmployee.getLastName() + "): ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD, " + existingEmployee.getDateOfBirth() + "): ");
        LocalDate dateOfBirth = parseDate(scanner.nextLine());
        System.out.print("Enter Email (" + existingEmployee.getEmail() + "): ");
        String email = scanner.nextLine();
        System.out.print("Enter Department (" + existingEmployee.getDepartment() + "): ");
        String department = scanner.nextLine();

        Employee employeeDetails = new Employee();
        employeeDetails.setFirstName(firstName.isEmpty() ? existingEmployee.getFirstName() : firstName);
        employeeDetails.setLastName(lastName.isEmpty() ? existingEmployee.getLastName() : lastName);
        employeeDetails.setDateOfBirth(dateOfBirth == null ? existingEmployee.getDateOfBirth() : dateOfBirth);
        employeeDetails.setEmail(email.isEmpty() ? existingEmployee.getEmail() : email);
        employeeDetails.setDepartment(department.isEmpty() ? existingEmployee.getDepartment() : department);

        employeeService.updateEmployee(id, employeeDetails);
        System.out.println("Employee updated successfully.");
    }

    private void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        Long id = Long.parseLong(scanner.nextLine());
        employeeService.deleteEmployee(id);
        System.out.println("Employee deleted successfully.");
    }

    private LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
            return null;
        }
    }
}
