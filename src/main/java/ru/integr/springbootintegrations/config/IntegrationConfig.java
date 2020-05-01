package ru.integr.springbootintegrations.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.MessageChannel;
import ru.integr.springbootintegrations.model.Student;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig {

    @Bean
    public MessageChannel recieverChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel replyChannel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "mappingToJSON.channel", outputChannel = "processAfterConvertJSON")
    public ObjectToJsonTransformer objectToJsonTransformer() {
        return new ObjectToJsonTransformer(getMapper()); //toJSON
    }

    @Bean
    public Jackson2JsonObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return new Jackson2JsonObjectMapper(mapper);
    }

    @Bean
    @Transformer(inputChannel = "convertJSONToObject.chanel", outputChannel = "processAfterConvertToObject.chanel")
    JsonToObjectTransformer jsonToObjectTransformer() {
        return new JsonToObjectTransformer(Student.class);
    }












}
