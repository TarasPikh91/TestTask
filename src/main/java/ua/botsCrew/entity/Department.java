package ua.botsCrew.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String departmentName;
    private String departmentHead;

    @ManyToMany
    @JoinTable(name = "lector_department", joinColumns = @JoinColumn(name = "department_id"), inverseJoinColumns = @JoinColumn(name = "lector_id"))
    private List<Lector> lectors = new ArrayList<Lector>();

    public Department(){

    }

    public Department(String departmentName, String departmentHead) {
        this.departmentName = departmentName;
        this.departmentHead = departmentHead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }

    public List<Lector> getLectors() {
        return lectors;
    }

    public void setLectors(List<Lector> lectors) {
        this.lectors = lectors;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", departmentHead='" + departmentHead + '\'' +
                '}';
    }
}
