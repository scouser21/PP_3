package boot.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan ("boot.springboot")
public class RESTConfig {

    @Bean
    public RestTemplate restTemplate (){
        return new RestTemplate();
    }
}
