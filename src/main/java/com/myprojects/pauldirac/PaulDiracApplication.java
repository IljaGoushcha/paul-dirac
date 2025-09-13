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

			// Change all last names to Bradley
			int numberOfUpdatedRecords = setLastNameForAllStudents(studentDAO,"Bradley");
			System.out.println("Number of entries updated to last name Bradley: " + numberOfUpdatedRecords);

			// Change user with id 155 last name to MacFarlane
			Student updatedStudent = updateStudentLastName(studentDAO,158, "MacFarlane");
			System.out.println("Updated student: " + updatedStudent.toString());

			// Fetch students by ID
			Student result = findStudent(studentDAO, 155);
			if (result != null) {
				System.out.println("***** Found the following student with id: 100 *****");
				System.out.println(result.toString());
				System.out.println("***** This is how update should look un DB *****");
				result.setFirstName("Hang");
				result.setLastName("Pham");
				result.setEmail("hang.pham@gmail.com.");
				System.out.println(result.toString());
				updateStudent(studentDAO, result);
			}

			// Delete student with id=160
			deleteStudentByStudentID(studentDAO, 160);

			// Delete all Bradleys
			int numberOfRowsDeleted = deleteAllStudentsWithLastName(studentDAO, "Bradley");
			System.out.println("Deleted " + numberOfRowsDeleted + " rows.");
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

	private int setLastNameForAllStudents(StudentDAO studentDAO, String lastName) {
		return studentDAO.updateAllLastNames(lastName);
	}

	private Student updateStudentLastName(StudentDAO studentDAO, int id, String lastName) {
		Student updatedStudent = studentDAO.updateLastNameById(id, lastName);
		return updatedStudent;
	}

	private Student updateStudent(StudentDAO studentDAO, Student student) {
		Student updatedStudent = studentDAO.update(student);
		return updatedStudent;
	}

	private void deleteStudentByStudentID(StudentDAO studentDAO, int id) {
		studentDAO.deleteStudent(id);
	}

	private int deleteAllStudentsWithLastName(StudentDAO studentDAO, String lastName) {
		int result = studentDAO.deleteAllStudentsWithLastName(lastName);
		return result;
	}

}
