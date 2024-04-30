package com.solurion.eclipto.workspace;

import com.solurion.eclipto.common.annotation.EcliptoResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EcliptoResourceServer
public class WorkspaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkspaceApplication.class);
    }
}
