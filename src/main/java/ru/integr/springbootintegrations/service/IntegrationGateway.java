package ru.integr.springbootintegrations.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.integr.springbootintegrations.model.Student;

@MessagingGateway
public interface IntegrationGateway {
    @Gateway(requestChannel = "integration.gateway.channel")
    public String sendMessage(String message);

    @Gateway(requestChannel = "integration.student.gateway.channel")
    public String processStudentDetails(Student student);

    @Gateway(requestChannel = "transformM1toStudent")
    public Student processM1toM2(String name);

}
