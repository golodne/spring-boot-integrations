package ru.integr.springbootintegrations.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface IntegrationGateway {
    @Gateway(requestChannel = "integration.gateway.channel")
    public String sendMessage(String message);
   // @Gateway(requestChannel = "studentChannel")
   // public Student processStudentDetails(Student student);
}
