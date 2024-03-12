package carolineeklund.shop_inventory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * Configuration class for application beans.
 * RestTemplate is a Spring class used for making HTTP requests.
 */
@Configuration
public class AppConfig {

    /**
     * Bean definition for creating a RestTemplate instance.
     *
     * @return A new instance of RestTemplate.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
