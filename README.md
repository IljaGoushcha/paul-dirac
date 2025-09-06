# paul-dirac

#Security
##To add security for actuator endpoints
These will protect your API and Actuator endpoints. This can be further fine-tuned using:

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/**").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic();
        return http.build();
    }
}

Use the following environment variables
ACTUATOR_NAME=something1;ACTUATOR_SYKRET=something2

#Building package
#With Maven
In terminal
export ACTUATOR_NAME=something1
export ACTUATOR_SYKRET=something2
mvn clean package