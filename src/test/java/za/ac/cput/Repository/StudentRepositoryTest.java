/*StudentRepositoryTest.java
Student Repository Test class
Author: Salaamah Peck (230207170)
Date: 23 March 2025
 */
package za.ac.cput.Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.Domain.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    private IStudentRepository repository;
    private Student student;


    @BeforeEach
    void setUp() {
        repository = StudentRepository.getRepository();

        student = new Student.StudentBuilder()
                .setStudentID("12345")
                .setStudentName("John")
                .setStudentSurname("Cena")
                .setStudentEmail("jcenta@mycput.ac.za")
                .setStudentPhone("0811234567")
                .build();
        repository.create(student);
    }

   @Test
   //Test adding a student to list
    void createdStudent() {
        assertEquals(1, repository.getAll().size());

   }

   @Test
   //Test retrieving a student
    void readStudent() {
        Student retrieved = repository.read("12345");
        assertNotNull(retrieved, "Student cannot be null");
        assertEquals("John", retrieved.getStudentName());
        assertEquals("Cena", retrieved.getStudentSurname());
        assertEquals("0811234567", retrieved.getStudentPhone());

   }

   @Test
       //Test updating a students details
    void updateStudent() {
        //Confirm existing details
    Student existingStudent = repository.read("12345");
    assertNotNull(existingStudent, "Student cannot be null");
    assertEquals("John", existingStudent.getStudentName());

    //update existing student
    Student updatedStudent = new Student.StudentBuilder()
             .setStudentID("12345")
             .setStudentName("Jane")
             .setStudentSurname("Cena")
             .setStudentEmail("jcena@gmail.com")
             .setStudentPhone("0814793232")
             .build();
     repository.update(updatedStudent);

     //Verify details have been changed
     Student retrieved = repository.read("12345");
     assertEquals("Jane", retrieved.getStudentName());
     assertEquals("Cena", retrieved.getStudentSurname());
     assertEquals("0814793232", retrieved.getStudentPhone());

   }

   @Test
       //Test deleting a student
    void deleteStudent() {
        boolean deleted = repository.delete("12345");
        assertTrue(deleted);
        assertEquals(0, repository.getAll().size());
   }
}