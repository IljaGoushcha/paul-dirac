package com.myprojects.pauldirac;

import com.myprojects.pauldirac.dao.StudentDAO;
import com.myprojects.pauldirac.dao.StudentDAOImpl;
import com.myprojects.pauldirac.entity.Student;
import jakarta.persistence.TypedQuery;
import org.hibernate.annotations.processing.Find;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PaulDiracApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaulDiracApplication.class, args);
	}

	@Bean()
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			System.out.println("Hello World.");

			// Seed DB with students
			createStudent(studentDAO);
			createMultipleStudents(studentDAO);

			// Fetch ALL Students
			Student result = findStudent(studentDAO, 100);
			if (result != null) { System.out.println(result.toString()); }

			// Finding all students
			List<Student> allStudentsInDatabase = queryForStudents(studentDAO);
			for (Student student : allStudentsInDatabase) {
				System.out.println(student.toString());
			}

			// Find all Goushchas
			List<Student> allGoushchaStudents = queryForStudentsWithLastName(studentDAO, "Goushcha");
			for (Student student : allGoushchaStudents) {
				System.out.println(student.toString());
			}
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		Student newStudent = new Student("Ilja", "Goushcha", "iljagou@yahoo.com");
		studentDAO.save(newStudent);
		System.out.println("New student created with id: " + newStudent.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student("Bob", "DoeOne", "bob@yahoo.com"));
		students.add(new Student("Mike", "DoeTwo", "mike@yahoo.com"));
		students.add(new Student("John", "DoeThree", "john@yahoo.com"));

		for (Student student : students) {
			studentDAO.save(student);
			System.out.println("New student created with id: " + student.getId());
		};
	}

	private Student findStudent(StudentDAO studentDAO, Integer studentId) {
		return studentDAO.findById(studentId);
	}

	private List<Student> queryForStudents(StudentDAO studentDAO) {
		return studentDAO.findAll();
	}

	private List<Student> queryForStudentsWithLastName(StudentDAO studentDAO, String lastName) {
		return studentDAO.findByLastName(lastName);
	}

}
