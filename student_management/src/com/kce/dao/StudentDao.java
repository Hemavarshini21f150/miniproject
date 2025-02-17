package com.kce.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.kce.bean.Student;
import com.kce.util.DBUtil;

public class StudentDao {
	 //Scanner sc = new Scanner(System.in);
	public  void insert(int a) {
	try {
		Scanner sc = new Scanner(System.in);
	Student[] arr = new Student[a];
	for (int i = 0; i < a; i++) {
	System.out.println("Enter Student id: ");
	int student_id = sc.nextInt();
	System.out.println("Enter Student name: ");
	String name = sc.next();
	System.out.println("Enter Student age: ");
	int age = sc.nextInt();
	System.out.println("Enter Student gender: ");
	String gender = sc.next();
	System.out.println("Enter the contact number: ");
	String contact_number = sc.next();
	System.out.println("Enter the Student email ");
	String email = sc.next();
	System.out.println("Enter the Student address ");
	String address = sc.next();


	arr[i] = new Student(student_id, name,age, gender,contact_number,email,address);
	}

	Connection con = DBUtil.getConnection();
	PreparedStatement stmt = con.prepareStatement("insert into Student values(?,?,?,?,?,?,?,?,?)");
	for (int i = 0; i < a; i++) {
	stmt.setInt(1, arr[i].getstudent_id());
	stmt.setString(2, arr[i].getName());
	stmt.setInt(3, arr[i].getAge());
	stmt.setString(4, arr[i].getGender());
	stmt.setString(5, arr[i].getContact_number());
	stmt.setString(6, arr[i].getEmail());
	stmt.setString(6, arr[i].getAddress());
	stmt.executeUpdate();
	}
	//con.close();
     //displayTable();
	System.out.println("Data Inserted successfully");
	sc.close();
	} catch (SQLException e) {
	System.out.println(e);
	} 
	}

	public  void update() {
	try {
		Scanner sc = new Scanner(System.in);
	Connection con = DBUtil.getConnection();
	PreparedStatement stmt = con.prepareStatement("UPDATE Student SET age = ? WHERE id = ?");
	System.out.println("Enter Student id: ");
	 int id = sc.nextInt();
	System.out.println("Enter Student age: ");
	 int age = sc.nextInt();
	stmt.setInt(1, age);
	stmt.setInt(2, id);
	int rowsAffected = stmt.executeUpdate();
	if (rowsAffected > 0) {
	System.out.println("Update successful");
	} else {
	System.out.println("No records found for the given Student id");
	}
	con.close();
	sc.close();
	} catch (SQLException e) {
	System.out.println(e);
	}
	}

	public  void delete() {
	try {
		Scanner sc = new Scanner(System.in);
	Connection con = DBUtil.getConnection();
	PreparedStatement stmt = con.prepareStatement("DELETE FROM Student WHERE name = ?");
	System.out.println("Enter Student name: ");
	String name = sc.next();
	stmt.setString(1, name);
	displayTable();
	int rowsAffected = stmt.executeUpdate();
     
	if (rowsAffected > 0) {
	System.out.println("Delete successful");
	} else {
	System.out.println("No records found for the given Student name");
	}
	//con.close();
	sc.close();
	} catch (SQLException e) {
	System.out.println(e);
	}
	}
	public  void displayTable() {
		try {
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM Student");
		ResultSet rs = stmt.executeQuery();

		System.out.println("Student Details:");
		System.out.println("---------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-17s | %-17s | %-17s | %-17s | %-17s | %-17s | %-17s | %-17s |%n", "id", "name", "age","Department","joindate","gender","contact","email");

		while (rs.next()) {

		System.out.printf("| %-17s | %-17s | %-17s | %-17s | %-17s | %-17s | %-17s | %-17s |%n",rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7),rs.getString(8));
		}

		rs.close();
		
		//con.close();
		} catch (SQLException e) {
		System.out.println(e);
		}
		}
	}

