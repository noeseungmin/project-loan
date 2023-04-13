package com.project_loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjectLoanApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectLoanApplication.class, args);
    }

}
