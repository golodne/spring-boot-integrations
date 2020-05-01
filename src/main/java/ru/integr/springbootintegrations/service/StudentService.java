package ru.integr.springbootintegrations.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ru.integr.springbootintegrations.model.Student;

@Component
public class StudentService {
    @ServiceActivator(inputChannel = "integration.student.objectToJson",
                      outputChannel = "integration.student.jsonToObject.fromTransformer")
    public Message<?> recieveMessage(Message<?> message) throws MessagingException {
        System.out.println("##############################");
        System.out.println(message);
        System.out.println("##############################");
        System.out.println("Object to JSON - " + message.getPayload());
        return message;
    }

    @ServiceActivator(inputChannel = "integration.student.jsonToObject.fromTransformer")
    public void processJsonToObject(Message<?> message) throws MessagingException {
        MessageChannel replayChannel = (MessageChannel) message.getHeaders().getReplyChannel();
        MessageBuilder.fromMessage(message);
        System.out.println("Json to Object " + message.getPayload());
        Student student = (Student) message.getPayload();
        Message<?> mewMessage = MessageBuilder.withPayload(student.toString()).build();
        replayChannel.send(mewMessage);
    }

}