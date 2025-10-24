package com.practice.micro.all.db.controller;

import com.practice.micro.all.db.dto.OrderDTO;
import com.practice.micro.all.db.dto.StudentDTO;
import com.practice.micro.all.db.entity.Order;
import com.practice.micro.all.db.entity.Student;
import com.practice.micro.all.db.service.OrderSevice;
import com.practice.micro.all.db.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    private StudentService service;

    @Autowired
    private ModelMapper modelMapper;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping(value = "/filterStudents")
    public List<StudentDTO> filterStudents(@RequestParam("name") String name) throws Exception {
        List<Student> entity= service.filterStudents(name);
        List<StudentDTO> studentList = modelMapper.map(entity, List.class);
        return studentList;

    }
}
