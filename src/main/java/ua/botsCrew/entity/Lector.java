package ua.botsCrew.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private double salary;
    private String degreeLevel;

    @ManyToMany
    @JoinTable(name = "lector_department", joinColumns = @JoinColumn(name="lector_id"), inverseJoinColumns = @JoinColumn(name="department_id"))
    private List<Department> departments = new ArrayList<Department>();

    public Lector() {
    }

    public Lector(String firstName, String lastName, double salary, String degreeLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.degreeLevel = degreeLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDegreeLevel() {
        return degreeLevel;
    }

    public void setDegreeLevel(String degreeLevel) {
        this.degreeLevel = degreeLevel;
    }


    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", degreeLevel='" + degreeLevel + '\'' +
                ", departments=" + departments +
                '}';
    }
}
