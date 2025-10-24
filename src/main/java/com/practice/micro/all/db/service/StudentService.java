package com.practice.micro.all.db.service;

import com.practice.micro.all.db.entity.Product;
import com.practice.micro.all.db.entity.Student;
import com.practice.micro.all.db.repository.StudentRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository   studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveAllStudents() {
        List<Student> students=List.of(Student.builder().name("Pervez").email("pervezg").age(37).address("tirupathi").build(),
                Student.builder().name("Salma").email("SalmaS").age(34).address("tirupathi").build(),
                Student.builder().name("Kalam").email("KalamA").age(7).address("tirupathi").build(),
                Student.builder().name("Zoha").email("ZohaF").age(37).address("USA").build(),
                Student.builder().name("Sabiha").email("SabihaS").age(37).address("USA").build());
        studentRepository.saveAll(students);
    }

    public List<Student> filterStudents(String name) {
        Specification<Student> spec = Specification
                .anyOf(StudentSpecification.hasName(name))
                .or(StudentSpecification.hasEmail(name))
                .or(StudentSpecification.hasAddress(name));
        return studentRepository.findAll(spec);
    }
}
