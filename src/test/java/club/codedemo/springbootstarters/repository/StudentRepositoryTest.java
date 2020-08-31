package club.codedemo.springbootstarters.repository;

import club.codedemo.springbootstarters.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void saveAndFind() {
        Student student = new Student();
        student.setName("zhangsan");
        this.studentRepository.save(student);
        assertNotNull(student.getId());

        student = this.studentRepository.findById(student.getId()).get();
        assertEquals("zhangsan",
                student.getName());
    }
}