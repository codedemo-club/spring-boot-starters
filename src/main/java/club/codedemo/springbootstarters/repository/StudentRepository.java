package club.codedemo.springbootstarters.repository;

import club.codedemo.springbootstarters.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
