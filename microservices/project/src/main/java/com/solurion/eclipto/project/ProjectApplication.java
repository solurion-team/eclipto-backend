package com.solurion.eclipto.project;

import com.solurion.eclipto.common.annotation.EcliptoResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EcliptoResourceServer
public class ProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class);
    }
}
