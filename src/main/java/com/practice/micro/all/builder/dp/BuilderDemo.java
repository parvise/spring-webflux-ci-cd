package com.practice.micro.all.builder.dp;

public class BuilderDemo {
    public static void main(String[] args) {
        User user = new User.UserBuilder(1, "John Doe")
                .email("abc@hp.com").phone("1234567890").age(30).address("123 Main St").build();
        User user2 = new User.UserBuilder(2, "ABC").build();
        System.out.println("User created: " + user);
        System.out.println("User created: " + user2);

    }
}
