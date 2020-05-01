package ru.integr.springbootintegrations.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.integr.springbootintegrations.model.Student;
import ru.integr.springbootintegrations.service.IntegrationGateway;

@RestController
@RequestMapping("/integrate")
public class IntegrationController {

    @Autowired
    private IntegrationGateway integrationGateway;

    @RequestMapping(value = "{name}")
    public String getMessageFromIntegrationService(@PathVariable("name") String name) {
        return integrationGateway.sendMessage(name);
    }

    @PostMapping
    public String convertToMessage(@RequestBody Student request) {
        return integrationGateway.processStudentDetails(request);
    }
}
