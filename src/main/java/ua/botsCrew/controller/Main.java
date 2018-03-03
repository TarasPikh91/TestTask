package ua.botsCrew.controller;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.botsCrew.dao.DepartmentDao;
import ua.botsCrew.dao.LectorDao;
import ua.botsCrew.daoImpl.DepartmentDaoImpl;
import ua.botsCrew.daoImpl.LectorDaoImpl;
import ua.botsCrew.entity.Department;
import ua.botsCrew.entity.Lector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("primary");

	private static EntityManager manager = factory.createEntityManager();

	public static void main(String[] args) throws SQLException {

		menu();


	}

	public static void menu(){

		int number;
		do {
			System.out.println("Press 1 if you want to add lector");
			System.out.println("Press 2 if you want add department");
			System.out.println("Press 3 if you want to know who is head of Department");
			System.out.println("Press 4 if you want to know department statistics");
			System.out.println("Press 5 if you want to know department average salary");
			System.out.println("Press 6 if you want to know count of employee in department");
			System.out.println("Press 7 if you want to find lector");
			System.out.println("Press 8to exit");
			number = scanner.nextInt();
			switch (number) {
				case 1:
					saveLector();
					break;
				case 2:
					saveDepartment();
					break;
				case 3:
					headOfDepartment();
					break;
				case 4:
					departmentStatistic();
					break;
				case 5:
					averageDepartmentSalary();
					break;
				case 6:
					countOfEmployeeDepartment();
					break;
				case 7:
					search();
					break;

				default:

					break;
			}
		}while (number != 8);

		manager.close();
		factory.close();
	}


	public static void search() {

		LectorDao lectorDao = new LectorDaoImpl(manager);
		System.out.println("enter first name or last name what you want to search");
		String search = scanner.next();
		for (Lector lector : lectorDao.findAll()) {
			if (lector.getFirstName().toLowerCase().contains(search.toLowerCase()) ||
					lector.getLastName().toLowerCase().contains(search.toLowerCase())) {
				System.out.println(lector.getFirstName() + " " + lector.getLastName());
			}
		}


	}


	public static  void countOfEmployeeDepartment() {
		DepartmentDao departmentDao = new DepartmentDaoImpl(manager);

		for (Department department : departmentDao.findAll()) {
			System.out.println(department.getId() + " " + department.getDepartmentName());
		}
		System.out.println("select department");
		int id = scanner.nextInt();
		Department department = departmentDao.findOne(id);
		System.out.println("show count of employee for " + department.getDepartmentName());
		int countOfEmployee = 0;
		for (Lector lector : department.getLectors()) {
			countOfEmployee++;
		}
		System.out.println(countOfEmployee);
	}

	public static void averageDepartmentSalary() {

		DepartmentDao departmentDao = new DepartmentDaoImpl(manager);

		for (Department department : departmentDao.findAll()) {
			System.out.println(department.getId() + " " + department.getDepartmentName());
		}
		System.out.println("select department");
		int id = scanner.nextInt();
		Department department = departmentDao.findOne(id);
		System.out.println("show the average salary for department " + department.getDepartmentName());
		double salary = 0;
		int countOfLector = 0;
		for (Lector lector : department.getLectors()) {
			countOfLector++;
			salary += lector.getSalary();
		}
		double averageSalary = salary / countOfLector;
		System.out.println("The average salary of " + department.getDepartmentName() + " " + averageSalary);
	}


	public static void departmentStatistic() {
		DepartmentDao departmentDao = new DepartmentDaoImpl(manager);

		for (Department department : departmentDao.findAll()) {
			System.out.println(department.getId() + " " + department.getDepartmentName());
		}
		System.out.println("select department");
		int id = scanner.nextInt();
		Department department = departmentDao.findOne(id);
		System.out.println("show department statistics " + department.getDepartmentName());
		int assistantCount = 0;
		int associateProfessorCount = 0;
		int professorCount = 0;
		for (Lector lector : department.getLectors()) {
			if (lector.getDegreeLevel().equals("assistant")) {
				assistantCount++;
			} else if (lector.getDegreeLevel().equals("associate professor")) {
				associateProfessorCount++;
			} else if (lector.getDegreeLevel().equals("professor")) {
				professorCount++;
			}
		}
		System.out.println("assistants: " + assistantCount + "\n" +
				"associate professor: " + associateProfessorCount + "\n" +
				"professor: " + professorCount);

	}

	public static void headOfDepartment() {
		DepartmentDao departmentDao = new DepartmentDaoImpl(manager);

		for (Department department : departmentDao.findAll()) {
			System.out.println(department.getId() + " " + department.getDepartmentName());
		}
		System.out.println("select department");
		int id = scanner.nextInt();
		Department department = departmentDao.findOne(id);
		System.out.println("who is head of " + department.getDepartmentName());
		for (Department department1 : departmentDao.findAll()) {
			if (department1.equals(department)) {
				System.out.println("Head of " + department.getDepartmentName() + " department is " + department1.getDepartmentHead());
			}
		}
	}

	public static void saveDepartment(){
		System.out.println("Enter department name");
		String departmentName = scanner.next();
		System.out.println("enter firstName and lastName department head");
		int number = 0;
		LectorDao lectorDao = new LectorDaoImpl(manager);
		for (Lector lector : lectorDao.findAll()){
			number++;
			System.out.println(number+" "+lector.getFirstName()+" "+lector.getLastName());
		}
		String departmentHead = scanner.next();
		Department department = new Department(departmentName, departmentHead);
		DepartmentDao departmentDao = new DepartmentDaoImpl(manager);
		departmentDao.save(department);

	}

	public static void saveLector(){

		System.out.println("Enter firstName");
		String firstName = scanner.next();
		System.out.println("Enter lastName");
		String lastName = scanner.next();
		System.out.println("Enter salary");
		double salary = scanner.nextDouble();
		System.out.println("Enter degry level: \n 1. assistant \n 2. associate professor \n 3. professor");
		String degreeLevel = null;
		int chooseDegree = scanner.nextInt();
		if (chooseDegree == 1){
			degreeLevel = "assistant";
		}else if(chooseDegree == 2){
			degreeLevel = "associate professor";
		}else if(chooseDegree == 3){
			degreeLevel = "professor";
		}else{
			System.out.println("You entered wrong number");
		}
		System.out.println("select departments");

		DepartmentDao departmentDao = new DepartmentDaoImpl(manager);

		for (Department department : departmentDao.findAll()){
			System.out.println(department.getId()+" "+department.getDepartmentName());
		}
		System.out.println("please enter count of departments where the lector works");
		int count = scanner.nextInt();
		System.out.println("select department");

		List <Department> departments = new ArrayList<>();
		List<Integer> listDepartmentIds = new ArrayList<>();
		for (int a = 0; a<count; a++){
			int deraprtmentId = scanner.nextInt();
			departments.add(departmentDao.findOne(deraprtmentId));
			listDepartmentIds.add(deraprtmentId);
		}
		Lector lector = new Lector(firstName, lastName,salary, degreeLevel);
		LectorDao lectorDao = new LectorDaoImpl(manager);
		lector.setDepartments(departments);
		for (Integer id : listDepartmentIds){
			System.out.println(id);
		}
		lectorDao.save(lector);


	}
}
