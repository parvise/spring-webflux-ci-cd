package com.practice.micro.all.db.service;


import com.practice.micro.all.db.entity.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {
    public static Specification<Student> hasName(String name) {
        return (root, query, cb) ->
                name == null ? null :  cb.equal(cb.function("binary", String.class, root.get("name")), name);
    }

    public static Specification<Student> hasEmail(String email) {
        return (root, query, cb) ->
                email == null ? null : cb.equal(cb.function("binary", String.class, root.get("email")), email);
    }

    public static Specification<Student> hasAddress(String address) {
        return (root, query, cb) ->
                address == null ? null : cb.equal(cb.function("binary", String.class, root.get("address")), address);
    }
}
