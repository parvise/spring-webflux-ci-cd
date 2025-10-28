package com.practice.micro.all.db.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDTO {

    private int id;
    private String name;
    private int age;
    private String email;
    private String address;
}
