//package com.example.demo.employee;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class EmployeeConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(EmployeeRepo repository) {
//        return args -> {
//            Employee ritik = new Employee("ABC", "DEF",
//                    "GHI", 2);
//
//            Employee yogesh = new Employee("abc", "def",
//                    "ghi", 2);
//
//            repository.saveAll(
//                    List.of(ritik, yogesh)
//            );
//        };
//    }
//}
