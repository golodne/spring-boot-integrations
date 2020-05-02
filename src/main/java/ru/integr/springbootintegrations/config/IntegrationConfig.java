package ru.integr.springbootintegrations.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.MessageChannel;
import ru.integr.springbootintegrations.model.Address;
import ru.integr.springbootintegrations.model.Student;

import java.util.HashMap;
import java.util.Map;

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

    @ServiceActivator(inputChannel = "router.channel")
    @Bean
    public PayloadTypeRouter router() {
        PayloadTypeRouter router = new PayloadTypeRouter();
        router.setChannelMapping(Student.class.getName(),"student.channel");
        router.setChannelMapping(Address.class.getName(),"address.channel");
        return router;
    }

    /*
    //modify some header
    @Bean
    @Transformer(inputChannel = "integration.student.gateway.channel",
                 outputChannel = "mappingToJSON.channel")
    public HeaderEnricher enricherHeader() {
        Map<String, HeaderValueMessageProcessor<String>> headersToAdd = new HashMap<>();
        headersToAdd.put("header1", new StaticHeaderValueMessageProcessor<String>("Test Header 1"));
        headersToAdd.put("header2", new StaticHeaderValueMessageProcessor<String>("Test Header 2"));
        HeaderEnricher enricher = new HeaderEnricher(headersToAdd);
        return enricher;
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

*/


}
