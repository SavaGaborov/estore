package store.configuration;

import jdk.jfr.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class EventNotificationConfiguration {

    @Bean
    public Sinks.Many<Event> events() {
        return Sinks.many().replay().latest();
    }
}
