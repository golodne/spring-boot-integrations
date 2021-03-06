package ru.integr.springbootintegrations.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.integr.springbootintegrations.model.Student;

@MessagingGateway
public interface IntegrationGateway {
 //   @Gateway(requestChannel = "integration.gateway.channel")
//    public String sendMessage(String message);

    @Gateway(requestChannel = "router.channel")
    public <T> void process(T object);

}
