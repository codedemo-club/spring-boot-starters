package club.codedemo.springbootstarters.controller;

import club.codedemo.springbootstarters.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST控制器
 */
@RestController
@RequestMapping("student")
public class StudentController {
    public List<Student> students = new ArrayList<>();

    @GetMapping
    public List<Student> all() {
        return this.students;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Student student) {
        this.students.add(student);
    }

    @GetMapping("{id}")
    public Student findById(@PathVariable Long id) {
        return this.students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst().get();
    }
}
